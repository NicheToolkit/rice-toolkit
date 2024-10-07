package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>ValueType</code>
 * <p>The type value type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestField
 * @since Jdk1.8
 */
public enum ValueType implements RestField {
    /**
     * <code>SHORT</code>
     * <p>The short value type field.</p>
     */
    SHORT(1, "短整型","int2"),
    /**
     * <code>INTEGER</code>
     * <p>The integer value type field.</p>
     */
    INTEGER(2, "整型","int4"),
    /**
     * <code>LONG</code>
     * <p>The long value type field.</p>
     */
    LONG(3, "长整型","int8"),
    /**
     * <code>FLOAT</code>
     * <p>The float value type field.</p>
     */
    FLOAT(4, "单精度浮点类型","float4"),
    /**
     * <code>DOUBLE</code>
     * <p>The double value type field.</p>
     */
    DOUBLE(5, "双精度浮点类型","float8"),
    /**
     * <code>TEXT</code>
     * <p>The text value type field.</p>
     */
    TEXT(6, "文本类型","text"),
    /**
     * <code>DATE</code>
     * <p>The date value type field.</p>
     */
    DATE(7, "日期类型","timestamp"),
    /**
     * <code>BOOLEAN</code>
     * <p>The boolean value type field.</p>
     */
    BOOLEAN(8, "布尔类型","bool"),
    /**
     * <code>STRING</code>
     * <p>The string value type field.</p>
     */
    STRING(9, "字符串类型","varchar"),
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
     * <code>ValueType</code>
     * <p>Instantiates a new value type.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    ValueType(Integer key, String value, String field) {
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
     * <code>parseKey</code>
     * <p>The key method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The key return object is <code>ValueType</code> type.</p>
     * @see java.lang.Integer
     * @see org.springframework.lang.NonNull
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static ValueType parseKey(@NonNull Integer key) {
        ValueType typeEnum = RestValue.parseKey(ValueType.class, key);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    /**
     * <code>parseValue</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The value return object is <code>ValueType</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public static ValueType parseValue(@NonNull String value) {
        ValueType typeEnum = RestValue.parseValue(ValueType.class, value);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    /**
     * <code>parseField</code>
     * <p>The field method.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The field return object is <code>ValueType</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public static ValueType parseField(@NonNull String field) {
        ValueType typeEnum = RestField.parseField(ValueType.class, field);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    /**
     * <code>isPresent</code>
     * <p>The present method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return boolean <p>The present return object is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     */
    public static boolean isPresent(Integer key) {
        if (GeneralUtils.isEmpty(key)) {
            return false;
        }
        Map<Integer, ValueType> valueTypeEnums = Stream.of(ValueType.values()).collect(Collectors.toMap(ValueType::getKey, Function.identity()));
        return Optional.ofNullable(valueTypeEnums.get(key)).isPresent();
    }

    /**
     * <code>isContrast</code>
     * <p>The contrast method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return boolean <p>The contrast return object is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     */
    public static boolean isContrast(Integer key) {
        if (isPresent(key)) {
            ValueType valueType = parseKey(key);
            switch (valueType) {
                case INTEGER:
                case LONG:
                case FLOAT:
                case DOUBLE:
                case SHORT:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    /**
     * <code>isRange</code>
     * <p>The range method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return boolean <p>The range return object is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     */
    public static boolean isRange(Integer key) {
        return isContrast(key);
    }

    /**
     * <code>isEqual</code>
     * <p>The equal method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return boolean <p>The equal return object is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     */
    public static boolean isEqual(Integer key) {
        if (isPresent(key)) {
            ValueType valueType = parseKey(key);
            switch (valueType) {
                case STRING:
                case TEXT:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    /**
     * <code>isContain</code>
     * <p>The contain method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return boolean <p>The contain return object is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     */
    public static boolean isContain(Integer key) {
        if (isPresent(key)) {
            ValueType valueType = parseKey(key);
            switch (valueType) {
                case INTEGER:
                case LONG:
                case FLOAT:
                case DOUBLE:
                case SHORT:
                case TEXT:
                case STRING:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }


}
