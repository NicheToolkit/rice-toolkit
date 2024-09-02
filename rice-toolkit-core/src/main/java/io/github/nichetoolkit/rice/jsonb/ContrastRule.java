package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <code>ContrastRule</code>
 * <p>The type contrast rule class.</p>
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
public class ContrastRule extends JsonbRule<ContrastRule> {
    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>the <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    protected ValueType type = ValueType.LONG;
    /**
     * <code>value</code>
     * {@link java.lang.Object} <p>the <code>value</code> field.</p>
     * @see java.lang.Object
     */
    protected Object value;
    /**
     * <code>operation</code>
     * {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>the <code>operation</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ContrastOperation
     */
    protected ContrastOperation operation = ContrastOperation.EQUAL_OPERATION;

    /**
     * <code>ContrastRule</code>
     * Instantiates a new contrast rule.
     */
    public ContrastRule() {
    }

    /**
     * <code>ContrastRule</code>
     * Instantiates a new contrast rule.
     * @param name      {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param type      {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>the type parameter is <code>ValueType</code> type.</p>
     * @param value     {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param operation {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>the operation parameter is <code>ContrastOperation</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rice.jsonb.ContrastOperation
     */
    public ContrastRule(String name, ValueType type, Object value, ContrastOperation operation) {
        super(name);
        this.type = type;
        this.value = value;
        this.operation = operation;
    }

    /**
     * <code>ContrastRule</code>
     * Instantiates a new contrast rule.
     * @param builder {@link io.github.nichetoolkit.rice.jsonb.ContrastRule.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ContrastRule.Builder
     */
    public ContrastRule(ContrastRule.Builder builder) {
        super(builder);
        this.type = builder.type;
        this.value = builder.value;
        this.operation = builder.operation;
    }

    /**
     * <code>getType</code>
     * <p>the type getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>the type return object is <code>ValueType</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    public ValueType getType() {
        return type;
    }

    /**
     * <code>setType</code>
     * <p>the type setter method.</p>
     * @param type {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>the type parameter is <code>ValueType</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    public void setType(ValueType type) {
        this.type = type;
    }

    /**
     * <code>getValue</code>
     * <p>the value getter method.</p>
     * @return {@link java.lang.Object} <p>the value return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public Object getValue() {
        return value;
    }

    /**
     * <code>setValue</code>
     * <p>the value setter method.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * <code>getOperation</code>
     * <p>the operation getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>the operation return object is <code>ContrastOperation</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ContrastOperation
     */
    public ContrastOperation getOperation() {
        return operation;
    }

    /**
     * <code>setOperation</code>
     * <p>the operation setter method.</p>
     * @param operation {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>the operation parameter is <code>ContrastOperation</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ContrastOperation
     */
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

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.jsonb.JsonbRule.Builder
     * @since Jdk1.8
     */
    public static class Builder extends JsonbRule.Builder<ContrastRule> {
        /**
         * <code>type</code>
         * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>the <code>type</code> field.</p>
         * @see io.github.nichetoolkit.rice.jsonb.ValueType
         */
        protected ValueType type;
        /**
         * <code>value</code>
         * {@link java.lang.Object} <p>the <code>value</code> field.</p>
         * @see java.lang.Object
         */
        protected Object value;
        /**
         * <code>operation</code>
         * {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>the <code>operation</code> field.</p>
         * @see io.github.nichetoolkit.rice.jsonb.ContrastOperation
         */
        protected ContrastOperation operation;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        @Override
        public ContrastRule.Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>type</code>
         * <p>the method.</p>
         * @param type {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>the type parameter is <code>ValueType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.ContrastRule.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.jsonb.ValueType
         */
        public ContrastRule.Builder type(ValueType type) {
            this.type = type;
            return this;
        }

        /**
         * <code>type</code>
         * <p>the method.</p>
         * @param type {@link java.lang.Integer} <p>the type parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.ContrastRule.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public ContrastRule.Builder type(Integer type) {
            this.type = Optional.ofNullable(type).map(ValueType::parseKey).orElse(ValueType.LONG);
            return this;
        }

        /**
         * <code>value</code>
         * <p>the method.</p>
         * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.ContrastRule.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Object
         */
        public ContrastRule.Builder value(Object value) {
            this.value = value;
            return this;
        }

        /**
         * <code>operation</code>
         * <p>the method.</p>
         * @param operation {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>the operation parameter is <code>ContrastOperation</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.ContrastRule.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.jsonb.ContrastOperation
         */
        public ContrastRule.Builder operation(ContrastOperation operation) {
            this.operation = operation;
            return this;
        }

        /**
         * <code>operation</code>
         * <p>the method.</p>
         * @param operation {@link java.lang.Integer} <p>the operation parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.ContrastRule.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public ContrastRule.Builder operation(Integer operation) {
            this.operation = Optional.ofNullable(operation).map(ContrastOperation::parseKey).orElse(ContrastOperation.EQUAL_OPERATION);
            return this;
        }

        @Override
        public ContrastRule build() {
            return new ContrastRule(this);
        }
    }
}
