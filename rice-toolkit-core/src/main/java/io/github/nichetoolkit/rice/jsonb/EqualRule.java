package io.github.nichetoolkit.rice.jsonb;

import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <code>EqualRule</code>
 * <p>The equal rule class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.jsonb.JsonbRule
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
public class EqualRule extends JsonbRule<EqualRule> {
    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    protected ValueType type = ValueType.STRING;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see java.lang.String
     */
    protected String value;
    /**
     * <code>operation</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>operation</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.EqualOperation
     */
    protected EqualOperation operation = EqualOperation.EQUAL_OPERATION;

    /**
     * <code>EqualRule</code>
     * <p>Instantiates a new equal rule.</p>
     */
    public EqualRule() {
    }

    /**
     * <code>EqualRule</code>
     * <p>Instantiates a new equal rule.</p>
     * @param name      {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type      {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
     * @param value     {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param operation {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The operation parameter is <code>EqualOperation</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     * @see io.github.nichetoolkit.rice.jsonb.EqualOperation
     */
    public EqualRule(String name, ValueType type, String value, EqualOperation operation) {
        super(name);
        this.type = type;
        this.value = value;
        this.operation = operation;
    }

    /**
     * <code>EqualRule</code>
     * <p>Instantiates a new equal rule.</p>
     * @param builder {@link io.github.nichetoolkit.rice.jsonb.EqualRule.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.EqualRule.Builder
     */
    public EqualRule(EqualRule.Builder builder) {
        super(builder);
        this.type = builder.type;
        this.value = builder.value;
        this.operation = builder.operation;
    }

    /**
     * <code>getType</code>
     * <p>The get type getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The get type return object is <code>ValueType</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    public ValueType getType() {
        return type;
    }

    /**
     * <code>setType</code>
     * <p>The set type setter method.</p>
     * @param type {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    public void setType(ValueType type) {
        this.type = type;
    }

    /**
     * <code>getValue</code>
     * <p>The get value getter method.</p>
     * @return {@link java.lang.String} <p>The get value return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getValue() {
        return value;
    }

    /**
     * <code>setValue</code>
     * <p>The set value setter method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * <code>getOperation</code>
     * <p>The get operation getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The get operation return object is <code>EqualOperation</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.EqualOperation
     */
    public EqualOperation getOperation() {
        return operation;
    }

    /**
     * <code>setOperation</code>
     * <p>The set operation setter method.</p>
     * @param operation {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The operation parameter is <code>EqualOperation</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.EqualOperation
     */
    public void setOperation(EqualOperation operation) {
        this.operation = operation;
    }

    @Override
    public String toSql(@NonNull String alias) {
        return toSql(alias,"value");
    }


    @Override
    @SuppressWarnings("Duplicates")
    public String toSql(@NonNull String alias, @NonNull String variable) {
        if (GeneralUtils.isEmpty(this.type) || GeneralUtils.isEmpty(this.operation)) {
            return SqlBuilder.EMPTY;
        }
        if (EqualOperation.NOT_NULL_OPERATION != this.operation && GeneralUtils.isEmpty(this.value)) {
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

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.jsonb.JsonbRule.Builder
     * @since Jdk1.8
     */
    public static class Builder extends JsonbRule.Builder<EqualRule> {
        /**
         * <code>type</code>
         * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The <code>type</code> field.</p>
         * @see io.github.nichetoolkit.rice.jsonb.ValueType
         */
        protected ValueType type;
        /**
         * <code>value</code>
         * {@link java.lang.String} <p>The <code>value</code> field.</p>
         * @see java.lang.String
         */
        protected String value;
        /**
         * <code>operation</code>
         * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>operation</code> field.</p>
         * @see io.github.nichetoolkit.rice.jsonb.EqualOperation
         */
        protected EqualOperation operation;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        @Override
        public EqualRule.Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>type</code>
         * <p>The type method.</p>
         * @param type {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.EqualRule.Builder} <p>The type return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.jsonb.ValueType
         */
        public EqualRule.Builder type(ValueType type) {
            this.type = type;
            return this;
        }

        /**
         * <code>type</code>
         * <p>The type method.</p>
         * @param type {@link java.lang.Integer} <p>The type parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.EqualRule.Builder} <p>The type return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public EqualRule.Builder type(Integer type) {
            this.type = Optional.ofNullable(type).map(ValueType::parseKey).orElse(ValueType.LONG);
            return this;
        }

        /**
         * <code>value</code>
         * <p>The value method.</p>
         * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.EqualRule.Builder} <p>The value return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public EqualRule.Builder value(String value) {
            this.value = value;
            return this;
        }

        /**
         * <code>operation</code>
         * <p>The operation method.</p>
         * @param operation {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The operation parameter is <code>EqualOperation</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.EqualRule.Builder} <p>The operation return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.jsonb.EqualOperation
         */
        public EqualRule.Builder operation(EqualOperation operation) {
            this.operation = operation;
            return this;
        }

        /**
         * <code>operation</code>
         * <p>The operation method.</p>
         * @param operation {@link java.lang.Integer} <p>The operation parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.EqualRule.Builder} <p>The operation return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public EqualRule.Builder operation(Integer operation) {
            this.operation = Optional.ofNullable(operation).map(EqualOperation::parseKey).orElse(EqualOperation.EQUAL_OPERATION);
            return this;
        }

        @Override
        public EqualRule build() {
            return new EqualRule(this);
        }
    }
}
