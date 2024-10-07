package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <code>RangeRule</code>
 * <p>The type range rule class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.jsonb.JsonbRule
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RangeRule extends JsonbRule<RangeRule> {

    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    protected ValueType type = ValueType.STRING;
    /**
     * <code>startValue</code>
     * {@link java.lang.Object} <p>The <code>startValue</code> field.</p>
     * @see java.lang.Object
     */
    protected Object startValue;
    /**
     * <code>endValue</code>
     * {@link java.lang.Object} <p>The <code>endValue</code> field.</p>
     * @see java.lang.Object
     */
    protected Object endValue;
    /**
     * <code>operation</code>
     * {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The <code>operation</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.RangeOperation
     */
    protected RangeOperation operation = RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION;


    /**
     * <code>RangeRule</code>
     * <p>Instantiates a new range rule.</p>
     */
    public RangeRule() {
    }

    /**
     * <code>RangeRule</code>
     * <p>Instantiates a new range rule.</p>
     * @param name       {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type       {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
     * @param startValue {@link java.lang.Object} <p>The start value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @param operation  {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The operation parameter is <code>RangeOperation</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rice.jsonb.RangeOperation
     */
    public RangeRule(String name, ValueType type, Object startValue, Object endValue, RangeOperation operation) {
        super(name);
        this.type = type;
        this.startValue = startValue;
        this.endValue = endValue;
        this.operation = operation;
    }

    /**
     * <code>RangeRule</code>
     * <p>Instantiates a new range rule.</p>
     * @param builder {@link io.github.nichetoolkit.rice.jsonb.RangeRule.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.RangeRule.Builder
     */
    public RangeRule(RangeRule.Builder builder) {
        super(builder);
        this.type = builder.type;
        this.startValue = builder.startValue;
        this.endValue = builder.endValue;
        this.operation = builder.operation;
    }

    /**
     * <code>getType</code>
     * <p>The type getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type return object is <code>ValueType</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    public ValueType getType() {
        return type;
    }

    /**
     * <code>setType</code>
     * <p>The type setter method.</p>
     * @param type {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    public void setType(ValueType type) {
        this.type = type;
    }

    /**
     * <code>getStartValue</code>
     * <p>The start value getter method.</p>
     * @return {@link java.lang.Object} <p>The start value return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public Object getStartValue() {
        return startValue;
    }

    /**
     * <code>setStartValue</code>
     * <p>The start value setter method.</p>
     * @param startValue {@link java.lang.Object} <p>The start value parameter is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public void setStartValue(Object startValue) {
        this.startValue = startValue;
    }

    /**
     * <code>getEndValue</code>
     * <p>The end value getter method.</p>
     * @return {@link java.lang.Object} <p>The end value return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public Object getEndValue() {
        return endValue;
    }

    /**
     * <code>setEndValue</code>
     * <p>The end value setter method.</p>
     * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public void setEndValue(Object endValue) {
        this.endValue = endValue;
    }

    /**
     * <code>getOperation</code>
     * <p>The operation getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The operation return object is <code>RangeOperation</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.RangeOperation
     */
    public RangeOperation getOperation() {
        return operation;
    }

    /**
     * <code>setOperation</code>
     * <p>The operation setter method.</p>
     * @param operation {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The operation parameter is <code>RangeOperation</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.RangeOperation
     */
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

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.jsonb.JsonbRule.Builder
     * @since Jdk1.8
     */
    public static class Builder extends JsonbRule.Builder<RangeRule> {
        /**
         * <code>type</code>
         * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The <code>type</code> field.</p>
         * @see io.github.nichetoolkit.rice.jsonb.ValueType
         */
        protected ValueType type;
        /**
         * <code>startValue</code>
         * {@link java.lang.Object} <p>The <code>startValue</code> field.</p>
         * @see java.lang.Object
         */
        protected Object startValue;
        /**
         * <code>endValue</code>
         * {@link java.lang.Object} <p>The <code>endValue</code> field.</p>
         * @see java.lang.Object
         */
        protected Object endValue;
        /**
         * <code>operation</code>
         * {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The <code>operation</code> field.</p>
         * @see io.github.nichetoolkit.rice.jsonb.RangeOperation
         */
        protected RangeOperation operation;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        @Override
        public RangeRule.Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>type</code>
         * <p>The method.</p>
         * @param type {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.RangeRule.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.jsonb.ValueType
         */
        public RangeRule.Builder type(ValueType type) {
            this.type = type;
            return this;
        }

        /**
         * <code>type</code>
         * <p>The method.</p>
         * @param type {@link java.lang.Integer} <p>The type parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.RangeRule.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public RangeRule.Builder type(Integer type) {
            this.type = Optional.ofNullable(type).map(ValueType::parseKey).orElse(ValueType.LONG);
            return this;
        }

        /**
         * <code>start</code>
         * <p>The method.</p>
         * @param startValue {@link java.lang.Object} <p>The start value parameter is <code>Object</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.RangeRule.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.Object
         */
        public RangeRule.Builder start(Object startValue) {
            this.startValue = startValue;
            return this;
        }

        /**
         * <code>end</code>
         * <p>The method.</p>
         * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.RangeRule.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.Object
         */
        public RangeRule.Builder end(Object endValue) {
            this.endValue = endValue;
            return this;
        }

        /**
         * <code>operation</code>
         * <p>The method.</p>
         * @param operation {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The operation parameter is <code>RangeOperation</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.RangeRule.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.jsonb.RangeOperation
         */
        public RangeRule.Builder operation(RangeOperation operation) {
            this.operation = operation;
            return this;
        }

        /**
         * <code>operation</code>
         * <p>The method.</p>
         * @param operation {@link java.lang.Integer} <p>The operation parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.RangeRule.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
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
