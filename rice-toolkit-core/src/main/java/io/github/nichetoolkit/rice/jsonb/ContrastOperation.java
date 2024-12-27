package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestStamp;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>ContrastOperation</code>
 * <p>The contrast operation enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestStamp
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum ContrastOperation implements RestStamp {
    /**
     * <code>EQUAL_OPERATION</code>
     * <p>The equal operation contrast operation stamp.</p>
     */
    EQUAL_OPERATION(1, "=","target = values"),
    /**
     * <code>GREATER_OPERATION</code>
     * <p>The greater operation contrast operation stamp.</p>
     */
    GREATER_OPERATION(2, ">","target > values"),
    /**
     * <code>GREATER_EQUAL_OPERATION</code>
     * <p>The greater equal operation contrast operation stamp.</p>
     */
    GREATER_EQUAL_OPERATION(3, ">=","target >= values"),
    /**
     * <code>LESS_OPERATION</code>
     * <p>The less operation contrast operation stamp.</p>
     */
    LESS_OPERATION(4, "<","target < values"),
    /**
     * <code>LESS_EQUAL_OPERATION</code>
     * <p>The less equal operation contrast operation stamp.</p>
     */
    LESS_EQUAL_OPERATION(5, "<=","target <= values"),
    /**
     * <code>UNEQUAL_OPERATION</code>
     * <p>The unequal operation contrast operation stamp.</p>
     */
    UNEQUAL_OPERATION(6, "!=","target != values"),
    ;
    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>The <code>key</code> stamp.</p>
     * @see  java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> stamp.</p>
     * @see  java.lang.String
     */
    private final String value;
    /**
     * <code>stamp</code>
     * {@link java.lang.String} <p>The <code>stamp</code> stamp.</p>
     * @see  java.lang.String
     */
    private final String stamp;

    /**
     * <code>TARGET</code>
     * {@link java.lang.String} <p>The constant <code>TARGET</code> stamp.</p>
     * @see  java.lang.String
     */
    public static final String TARGET = "target";
    /**
     * <code>VALUE</code>
     * {@link java.lang.String} <p>The constant <code>VALUE</code> stamp.</p>
     * @see  java.lang.String
     */
    public static final String VALUE = "values";

    /**
     * <code>ContrastOperation</code>
     * <p>Instantiates a new contrast operation.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param stamp {@link java.lang.String} <p>The stamp parameter is <code>String</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.String
     */
    ContrastOperation(Integer key, String value, String stamp) {
        this.key = key;
        this.value = value;
        this.stamp = stamp;
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
    public String getStamp() {
        return this.stamp;
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
       return this.stamp.replace(TARGET, target).replace(VALUE, String.valueOf(value));
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
     * <code>parseStamp</code>
     * <p>The parse stamp method.</p>
     * @param stamp {@link java.lang.String} <p>The stamp parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>The parse stamp return object is <code>ContrastOperation</code> type.</p>
     */
    public static ContrastOperation parseStamp(String stamp) {
        ContrastOperation typeEnum = RestStamp.parseStamp(ContrastOperation.class, stamp);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }
}
