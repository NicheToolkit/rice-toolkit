package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>ContainRule</code>
 * <p>The contain rule class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.jsonb.JsonbRule
 * @see lombok.Getter
 * @see lombok.Setter
 * @see java.lang.SuppressWarnings
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@SuppressWarnings("WeakerAccess")
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContainRule extends JsonbRule<ContainRule> {

    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    protected ValueType type = ValueType.LONG;
    /**
     * <code>values</code>
     * {@link java.util.Set} <p>The <code>values</code> field.</p>
     * @see java.util.Set
     */
    protected Set<Object> values;

    /**
     * <code>ContainRule</code>
     * <p>Instantiates a new contain rule.</p>
     */
    public ContainRule() {
    }

    /**
     * <code>ContainRule</code>
     * <p>Instantiates a new contain rule.</p>
     * @param name   {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type   {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     */
    public ContainRule(String name, ValueType type, @NonNull Collection<Object> values) {
        super(name);
        this.type = type;
        this.values = new HashSet<>(values);
    }

    /**
     * <code>ContainRule</code>
     * <p>Instantiates a new contain rule.</p>
     * @param name   {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type   {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
     * @param values {@link java.lang.Object} <p>The values parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     * @see java.lang.Object
     * @see org.springframework.lang.NonNull
     */
    public ContainRule(String name, ValueType type, @NonNull Object... values) {
        super(name);
        this.type = type;
        this.values = new HashSet<>(Arrays.asList(values));
    }

    /**
     * <code>getValues</code>
     * <p>The get values getter method.</p>
     * @return {@link java.util.List} <p>The get values return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<Object> getValues() {
        if (GeneralUtils.isNotEmpty(values)) {
            return new ArrayList<>(values);
        }
        return null;
    }

    /**
     * <code>setValues</code>
     * <p>The set values setter method.</p>
     * @param values {@link java.lang.Object} <p>The values parameter is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public void setValues(Object... values) {
        this.values = Optional.ofNullable(values).map(childList -> new HashSet<>(Arrays.asList(childList))).orElse(null);
    }

    /**
     * <code>setValues</code>
     * <p>The set values setter method.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setValues(Collection<Object> values) {
        this.values = Optional.ofNullable(values).map(HashSet::new).orElse(null);
    }

    /**
     * <code>addValues</code>
     * <p>The add values method.</p>
     * @param values {@link java.lang.Object} <p>The values parameter is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public void addValues(Object... values) {
        if (GeneralUtils.isEmpty(this.values)) {
            this.values = Optional.ofNullable(values).map(childList -> new HashSet<>(Arrays.asList(childList))).orElse(null);
        } else {
            Optional.ofNullable(values).ifPresent(childList -> this.values.addAll(Arrays.asList(childList)));
        }
    }

    /**
     * <code>addValues</code>
     * <p>The add values method.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
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
}
