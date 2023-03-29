package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <p>ContainRule</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContainRule extends JsonbRule<ContainRule> {
    /** 属性值类型 */
    protected ValueType type = ValueType.LONG;
    /** 属性值运算范围 */
    protected Set<Object> values;

    public ContainRule() {
    }

    public ContainRule(String name, ValueType type, @NonNull Collection<Object> values) {
        super(name);
        this.type = type;
        this.values = new HashSet<>(values);
    }

    public ContainRule(String name, ValueType type, @NonNull Object... values) {
        super(name);
        this.type = type;
        this.values = new HashSet<>(Arrays.asList(values));
    }

    public ContainRule(ContainRule.Builder builder) {
        super(builder);
        this.type = builder.type;
        this.values = builder.values;
    }

    public ValueType getType() {
        return type;
    }

    public void setType(ValueType type) {
        this.type = type;
    }

    public void setValues(Set<Object> values) {
        this.values = values;
    }

    public List<Object> getValues() {
        if (GeneralUtils.isNotEmpty(values)) {
            return new ArrayList<>(values);
        }
        return null;
    }

    public void setValues(Object... values) {
        this.values = Optional.ofNullable(values).map(childList -> new HashSet<>(Arrays.asList(childList))).orElse(null);
    }

    @JsonSetter
    public void setValues(Collection<Object> values) {
        this.values = Optional.ofNullable(values).map(HashSet::new).orElse(null);
    }

    public void addValues(Object... values) {
        if (GeneralUtils.isEmpty(this.values)) {
            this.values = Optional.ofNullable(values).map(childList -> new HashSet<>(Arrays.asList(childList))).orElse(null);
        } else {
            Optional.ofNullable(values).ifPresent(childList -> this.values.addAll(Arrays.asList(childList)));
        }
    }

    public void addValues(Collection<Object> values) {
        if (GeneralUtils.isEmpty(this.values)) {
            this.values = Optional.ofNullable(values).map(HashSet::new).orElse(null);
        } else {
            Optional.ofNullable(values).ifPresent(this.values::addAll);
        }
    }

    @Override
    public String toSql(@NonNull String alias) {
        return toSql(alias,"value");
    }

    @Override
    public String toSql(@NonNull String alias, @NonNull String variable) {
        if (GeneralUtils.isEmpty(this.values) || GeneralUtils.isEmpty(this.type)) {
            return SqlBuilder.EMPTY;
        }
        SqlBuilder sqlBuilder = SqlBuilders.newSqlBuilder();
        if (ValueType.isContain(this.type.getKey())) {
            String target = target(alias, this.name, variable, this.type);
            sqlBuilder.in(target,this.values,true);
        }
        return sqlBuilder.toString();
    }

    public static class Builder extends JsonbRule.Builder<ContainRule> {
        protected ValueType type;
        protected Set<Object> values;

        public Builder() {
        }

        @Override
        public ContainRule.Builder name(String name) {
            this.name = name;
            return this;
        }

        public ContainRule.Builder type(ValueType type) {
            this.type = type;
            return this;
        }

        public ContainRule.Builder type(Integer type) {
            this.type = Optional.ofNullable(type).map(ValueType::parseKey).orElse(ValueType.LONG);
            return this;
        }

        public ContainRule.Builder values(Collection<Object> values) {
            this.values = Optional.ofNullable(values).map(HashSet::new).orElse(null);
            return this;
        }

        public ContainRule.Builder values(@NonNull Object... values) {
            this.values = Optional.of(values).map(valueList -> new HashSet<>(Arrays.asList(valueList))).orElse(null);
            return this;
        }

        @Override
        public ContainRule build() {
            return new ContainRule(this);
        }
    }
}
