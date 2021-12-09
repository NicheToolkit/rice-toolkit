package io.github.nichetoolkit.rice.jsonb;

import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <p>EqualRule</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class EqualRule extends JsonbRule<EqualRule> {
    /** 属性值类型 */
    protected ValueType type = ValueType.STRING;
    /** 属性值 */
    protected String value;
    /** 计算操作 */
    protected EqualOperation operation = EqualOperation.EQUAL_OPERATION;
    
    public EqualRule() {
    }

    public EqualRule(String name, ValueType type, String value, EqualOperation operation) {
        super(name);
        this.type = type;
        this.value = value;
        this.operation = operation;
    }

    public EqualRule(EqualRule.Builder builder) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EqualOperation getOperation() {
        return operation;
    }

    public void setOperation(EqualOperation operation) {
        this.operation = operation;
    }

    @Override
    public String toSql(@NonNull String alias) {
        return toSql(alias,"value");
    }

    @Override
    public String toSql(@NonNull String alias, @NonNull String variable) {
        if (GeneralUtils.isEmpty(this.value) || GeneralUtils.isEmpty(this.type) || GeneralUtils.isEmpty(this.operation)) {
            return SqlBuilder.EMPTY;
        }
        SqlBuilder sqlBuilder = SqlBuilders.newSqlBuilder();
        if (ValueType.isEqual(this.type.getKey())) {
            String target = target(alias, this.name, variable, this.type);
            String sql = this.operation.translateSql(target, this.value);
            sqlBuilder.andOfOr(true).append(sql);
        }
        return sqlBuilder.toString();
    }

    public static class Builder extends JsonbRule.Builder<EqualRule> {
        protected ValueType type;
        protected String value;
        protected EqualOperation operation;

        public Builder() {
        }

        public EqualRule.Builder name(String name) {
            this.name = name;
            return this;
        }

        public EqualRule.Builder type(ValueType type) {
            this.type = type;
            return this;
        }

        public EqualRule.Builder type(Integer type) {
            this.type = Optional.ofNullable(type).map(ValueType::parserKey).orElse(ValueType.LONG);
            return this;
        }

        public EqualRule.Builder value(String value) {
            this.value = value;
            return this;
        }

        public EqualRule.Builder operation(EqualOperation operation) {
            this.operation = operation;
            return this;
        }

        public EqualRule.Builder operation(Integer operation) {
            this.operation = Optional.ofNullable(operation).map(EqualOperation::parserKey).orElse(EqualOperation.EQUAL_OPERATION);
            return this;
        }

        @Override
        public EqualRule build() {
            return new EqualRule(this);
        }
    }
}
