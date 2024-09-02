package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>ContrastOperation</code>
 * <p>The type contrast operation enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestField
 * @since Jdk1.8
 */
public enum ContrastOperation implements RestField {
    /**
     * <code>EQUAL_OPERATION</code>
     * <p>the Equal operation contrast operation field.</p>
     */
    EQUAL_OPERATION(1, "相等","target = values"),
    /**
     * <code>GREATER_OPERATION</code>
     * <p>the Greater operation contrast operation field.</p>
     */
    GREATER_OPERATION(2, "大于","target > values"),
    /**
     * <code>GREATER_EQUAL_OPERATION</code>
     * <p>the Greater equal operation contrast operation field.</p>
     */
    GREATER_EQUAL_OPERATION(3, "大于等于","target >= values"),
    /**
     * <code>LESS_OPERATION</code>
     * <p>the Less operation contrast operation field.</p>
     */
    LESS_OPERATION(4, "小于","target < values"),
    /**
     * <code>LESS_EQUAL_OPERATION</code>
     * <p>the Less equal operation contrast operation field.</p>
     */
    LESS_EQUAL_OPERATION(5, "小于等于","target <= values"),
    /**
     * <code>UNEQUAL_OPERATION</code>
     * <p>the Unequal operation contrast operation field.</p>
     */
    UNEQUAL_OPERATION(6, "不等于","target != values"),
    ;
    private final Integer key;
    private final String value;
    private final String field;

    /**
     * <code>TARGET</code>
     * {@link java.lang.String} <p>the constant <code>TARGET</code> field.</p>
     * @see java.lang.String
     */
    public static final String TARGET = "target";
    /**
     * <code>VALUE</code>
     * {@link java.lang.String} <p>the constant <code>VALUE</code> field.</p>
     * @see java.lang.String
     */
    public static final String VALUE = "values";

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
     * <p>the sql method.</p>
     * @param target {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.String} <p>the sql return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public String translateSql(String target, Object value) {
       return this.field.replace(TARGET, target).replace(VALUE, String.valueOf(value));
    }

    /**
     * <code>parseKey</code>
     * <p>the key method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>the key return object is <code>ContrastOperation</code> type.</p>
     * @see java.lang.Integer
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static ContrastOperation parseKey(Integer key) {
        ContrastOperation typeEnum = RestValue.parseKey(ContrastOperation.class, key);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }

    /**
     * <code>parseValue</code>
     * <p>the value method.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>the value return object is <code>ContrastOperation</code> type.</p>
     * @see java.lang.String
     */
    public static ContrastOperation parseValue(String value) {
        ContrastOperation typeEnum = RestValue.parseValue(ContrastOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }

    /**
     * <code>parseField</code>
     * <p>the field method.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>the field return object is <code>ContrastOperation</code> type.</p>
     * @see java.lang.String
     */
    public static ContrastOperation parseField(String field) {
        ContrastOperation typeEnum = RestField.parseField(ContrastOperation.class, field);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }
}
