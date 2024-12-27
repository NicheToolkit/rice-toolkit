package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>ContrastOperation</code>
 * <p>The contrast operation enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestField
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum ContrastOperation implements RestField {
    /**
     * <code>EQUAL_OPERATION</code>
     * <p>The equal operation contrast operation field.</p>
     */
    EQUAL_OPERATION(1, "相等","target = values"),
    /**
     * <code>GREATER_OPERATION</code>
     * <p>The greater operation contrast operation field.</p>
     */
    GREATER_OPERATION(2, "大于","target > values"),
    /**
     * <code>GREATER_EQUAL_OPERATION</code>
     * <p>The greater equal operation contrast operation field.</p>
     */
    GREATER_EQUAL_OPERATION(3, "大于等于","target >= values"),
    /**
     * <code>LESS_OPERATION</code>
     * <p>The less operation contrast operation field.</p>
     */
    LESS_OPERATION(4, "小于","target < values"),
    /**
     * <code>LESS_EQUAL_OPERATION</code>
     * <p>The less equal operation contrast operation field.</p>
     */
    LESS_EQUAL_OPERATION(5, "小于等于","target <= values"),
    /**
     * <code>UNEQUAL_OPERATION</code>
     * <p>The unequal operation contrast operation field.</p>
     */
    UNEQUAL_OPERATION(6, "不等于","target != values"),
    ;
    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>The <code>key</code> field.</p>
     * @see  java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see  java.lang.String
     */
    private final String value;
    /**
     * <code>field</code>
     * {@link java.lang.String} <p>The <code>field</code> field.</p>
     * @see  java.lang.String
     */
    private final String field;

    /**
     * <code>TARGET</code>
     * {@link java.lang.String} <p>The constant <code>TARGET</code> field.</p>
     * @see  java.lang.String
     */
    public static final String TARGET = "target";
    /**
     * <code>VALUE</code>
     * {@link java.lang.String} <p>The constant <code>VALUE</code> field.</p>
     * @see  java.lang.String
     */
    public static final String VALUE = "values";

    /**
     * <code>ContrastOperation</code>
     * <p>Instantiates a new contrast operation.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.String
     */
    ContrastOperation(Integer key, String value, String field) {
        this.key = key;
        this.value = value;
        this.field = field;
    }

    @JsonValue
    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getField() {
        return this.field;
    }

    /**
     * <code>translateSql</code>
     * <p>The translate sql method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @return  {@link java.lang.String} <p>The translate sql return object is <code>String</code> type.</p>
     */
    public String translateSql(String target, Object value) {
       return this.field.replace(TARGET, target).replace(VALUE, String.valueOf(value));
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @see  com.fasterxml.jackson.annotation.JsonCreator
     * @return  {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>The parse key return object is <code>ContrastOperation</code> type.</p>
     */
    @JsonCreator
    public static ContrastOperation parseKey(Integer key) {
        ContrastOperation typeEnum = RestValue.parseKey(ContrastOperation.class, key);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>The parse value return object is <code>ContrastOperation</code> type.</p>
     */
    public static ContrastOperation parseValue(String value) {
        ContrastOperation typeEnum = RestValue.parseValue(ContrastOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }

    /**
     * <code>parseField</code>
     * <p>The parse field method.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>The parse field return object is <code>ContrastOperation</code> type.</p>
     */
    public static ContrastOperation parseField(String field) {
        ContrastOperation typeEnum = RestField.parseField(ContrastOperation.class, field);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }
}
