package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.util.Optional;

@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RangeRule extends JsonbRule<RangeRule> {

    protected ValueType type = ValueType.STRING;
    protected Object startValue;
    protected Object endValue;
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

        @Override
        public RangeRule.Builder name(String name) {
            this.name = name;
            return this;
        }

        public RangeRule.Builder type(ValueType type) {
            this.type = type;
            return this;
        }

        public RangeRule.Builder type(Integer type) {
            this.type = Optional.ofNullable(type).map(ValueType::parseKey).orElse(ValueType.LONG);
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
            this.operation = Optional.ofNullable(operation).map(RangeOperation::parseKey).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
            return this;
        }

        @Override
        public RangeRule build() {
            return new RangeRule(this);
        }
    }
}
