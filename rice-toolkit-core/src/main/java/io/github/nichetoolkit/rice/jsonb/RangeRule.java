package io.github.nichetoolkit.rice.jsonb;

import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <p>RangeRule</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RangeRule extends JsonbRule<RangeRule> {

    /** 属性值类型 */
    protected ValueType type = ValueType.STRING;
    /** 属性开始值 */
    protected Object startValue;
    /** 属性结束值 */
    protected Object endValue;
    /** 计算操作 */
    protected RangeOperation operation = RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION;


    public RangeRule() {
    }

    public RangeRule(String name, ValueType type, Object startValue, Object endValue, RangeOperation operation) {
        super(name);
        this.type = type;
        this.startValue = startValue;
        this.endValue = endValue;
        this.operation = operation;
    }

    public RangeRule(RangeRule.Builder builder) {
        super(builder);
        this.type = builder.type;
        this.startValue = builder.startValue;
        this.endValue = builder.endValue;
        this.operation = builder.operation;
    }

    public ValueType getType() {
        return type;
    }

    public void setType(ValueType type) {
        this.type = type;
    }

    public Object getStartValue() {
        return startValue;
    }

    public void setStartValue(Object startValue) {
        this.startValue = startValue;
    }

    public Object getEndValue() {
        return endValue;
    }

    public void setEndValue(Object endValue) {
        this.endValue = endValue;
    }

    public RangeOperation getOperation() {
        return operation;
    }

    public void setOperation(RangeOperation operation) {
        this.operation = operation;
    }

    @Override
    public String toSql(@NonNull String alias) {
        return toSql(alias,"value");
    }

    @Override
    public String toSql(@NonNull String alias, @NonNull String variable) {
        if (GeneralUtils.isEmpty(this.startValue) || GeneralUtils.isEmpty(this.endValue)
                || GeneralUtils.isEmpty(this.type) || GeneralUtils.isEmpty(this.operation)) {
            return SqlBuilder.EMPTY;
        }
        SqlBuilder sqlBuilder = SqlBuilders.newSqlBuilder();
        if (ValueType.isRange(this.type.getKey())) {
            String target = target(alias, this.name, variable, this.type);
            String sql = this.operation.translateSql(target, this.startValue,this.endValue);
            sqlBuilder.andOfOr(true).append(sql);
        }
        return sqlBuilder.toString();
    }

    public static class Builder extends JsonbRule.Builder<RangeRule> {
        protected ValueType type;
        protected Object startValue;
        protected Object endValue;
        protected RangeOperation operation;

        public Builder() {
        }

        public RangeRule.Builder name(String name) {
            this.name = name;
            return this;
        }

        public RangeRule.Builder type(ValueType type) {
            this.type = type;
            return this;
        }

        public RangeRule.Builder type(Integer type) {
            this.type = Optional.ofNullable(type).map(ValueType::parserKey).orElse(ValueType.LONG);
            return this;
        }

        public RangeRule.Builder start(Object startValue) {
            this.startValue = startValue;
            return this;
        }

        public RangeRule.Builder end(Object endValue) {
            this.endValue = endValue;
            return this;
        }

        public RangeRule.Builder operation(RangeOperation operation) {
            this.operation = operation;
            return this;
        }

        public RangeRule.Builder operation(Integer operation) {
            this.operation = Optional.ofNullable(operation).map(RangeOperation::parserKey).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
            return this;
        }

        @Override
        public RangeRule build() {
            return new RangeRule(this);
        }
    }
}
