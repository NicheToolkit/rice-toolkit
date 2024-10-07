package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <code>RangeOperation</code>
 * <p>The type range operation enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestField
 * @since Jdk1.8
 */
public enum RangeOperation implements RestField {
    /**
     * <code>GREATER_EQUAL_LESS_EQUAL_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The <code>GREATER_EQUAL_LESS_EQUAL_OPERATION</code> field.</p>
     */
    GREATER_EQUAL_LESS_EQUAL_OPERATION(1, "大于等于且小于等于","(target >= start and target <= end)"),
    /**
     * <code>GREATER_EQUAL_LESS_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The <code>GREATER_EQUAL_LESS_OPERATION</code> field.</p>
     */
    GREATER_EQUAL_LESS_OPERATION(2, "大于等于且小于","(target >= start and target < end)"),
    /**
     * <code>GREATER_LESS_EQUAL_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The <code>GREATER_LESS_EQUAL_OPERATION</code> field.</p>
     */
    GREATER_LESS_EQUAL_OPERATION(3, "大于且小于等于","(target > start and target <= end)"),
    /**
     * <code>GREATER_LESS_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The <code>GREATER_LESS_OPERATION</code> field.</p>
     */
    GREATER_LESS_OPERATION(4, "大于且小于","(target > start and target < end)"),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>The <code>key</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see java.lang.String
     */
    private final String value;
    /**
     * <code>field</code>
     * {@link java.lang.String} <p>The <code>field</code> field.</p>
     * @see java.lang.String
     */
    private final String field;

    /**
     * <code>TARGET</code>
     * {@link java.lang.String} <p>The constant <code>TARGET</code> field.</p>
     * @see java.lang.String
     */
    public static final String TARGET = "target";
    /**
     * <code>START</code>
     * {@link java.lang.String} <p>The constant <code>START</code> field.</p>
     * @see java.lang.String
     */
    public static final String START = "start";
    /**
     * <code>END</code>
     * {@link java.lang.String} <p>The constant <code>END</code> field.</p>
     * @see java.lang.String
     */
    public static final String END = "end";

    /**
     * <code>RangeOperation</code>
     * <p>Instantiates a new range operation.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    RangeOperation(Integer key, String value, String field) {
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
     * <p>The sql method.</p>
     * @param target     {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param startValue {@link java.lang.Object} <p>The start value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.String} <p>The sql return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public String translateSql(String target, Object startValue, Object endValue) {
        return this.field.replace(TARGET, target)
                .replace(START, String.valueOf(startValue))
                .replace(END, String.valueOf(endValue));
    }

    /**
     * <code>parseKey</code>
     * <p>The key method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The key return object is <code>RangeOperation</code> type.</p>
     * @see java.lang.Integer
     * @see org.springframework.lang.NonNull
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static RangeOperation parseKey(@NonNull Integer key) {
        RangeOperation typeEnum = RestValue.parseKey(RangeOperation.class, key);
        return Optional.ofNullable(typeEnum).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
    }

    /**
     * <code>parseValue</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The value return object is <code>RangeOperation</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public static RangeOperation parseValue(@NonNull String value) {
        RangeOperation typeEnum = RestValue.parseValue(RangeOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
    }

    /**
     * <code>parseField</code>
     * <p>The field method.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The field return object is <code>RangeOperation</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public static RangeOperation parseField(@NonNull String field) {
        RangeOperation typeEnum = RestField.parseField(RangeOperation.class, field);
        return Optional.ofNullable(typeEnum).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
    }
}
