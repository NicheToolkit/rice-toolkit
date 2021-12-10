package io.github.nichetoolkit.rice.jsonb;

import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <p>ContrastRule</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class ContrastRule extends JsonbRule<ContrastRule> {

    /** 属性值类型 */
    protected ValueType type = ValueType.LONG;
    /** 属性值 */
    protected Object value;
    /** 计算操作 */
    protected ContrastOperation operation = ContrastOperation.EQUAL_OPERATION;

    public ContrastRule() {
    }

    public ContrastRule(String name, ValueType type, Object value, ContrastOperation operation) {
        super(name);
        this.type = type;
        this.value = value;
        this.operation = operation;
    }

    public ContrastRule(ContrastRule.Builder builder) {
        super(builder);
        this.type = builder.type;
        this.value = builder.value;
        this.operation = builder.operation;
    }

    public ValueType getType() {
        return type;
    }

    public void setType(ValueType type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ContrastOperation getOperation() {
        return operation;
    }

    public void setOperation(ContrastOperation operation) {
        this.operation = operation;
    }

    @Override
    public String toSql(@NonNull String alias) {
        return toSql(alias,"value");
    }

    @Override
    @SuppressWarnings("Duplicates")
    public String toSql(@NonNull String alias, @NonNull String variable) {
        if (GeneralUtils.isEmpty(this.value) || GeneralUtils.isEmpty(this.type) || GeneralUtils.isEmpty(this.operation)) {
            return SqlBuilder.EMPTY;
        }
        SqlBuilder sqlBuilder = SqlBuilders.newSqlBuilder();
        if (ValueType.isContrast(this.type.getKey())) {
            String target = target(alias, this.name, variable, this.type);
            String sql = this.operation.translateSql(target, this.value);
            sqlBuilder.andOfOr(true).append(sql);
        }
        return sqlBuilder.toString();
    }

    public static class Builder extends JsonbRule.Builder<ContrastRule> {
        protected ValueType type;
        protected Object value;
        protected ContrastOperation operation;

        public Builder() {
        }

        public ContrastRule.Builder name(String name) {
            this.name = name;
            return this;
        }

        public ContrastRule.Builder type(ValueType type) {
            this.type = type;
            return this;
        }

        public ContrastRule.Builder type(Integer type) {
            this.type = Optional.ofNullable(type).map(ValueType::parserKey).orElse(ValueType.LONG);
            return this;
        }

        public ContrastRule.Builder value(Object value) {
            this.value = value;
            return this;
        }

        public ContrastRule.Builder operation(ContrastOperation operation) {
            this.operation = operation;
            return this;
        }

        public ContrastRule.Builder operation(Integer operation) {
            this.operation = Optional.ofNullable(operation).map(ContrastOperation::parserKey).orElse(ContrastOperation.EQUAL_OPERATION);
            return this;
        }

        @Override
        public ContrastRule build() {
            return new ContrastRule(this);
        }
    }
}
