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
     * <p>the Short value type field.</p>
     */
    SHORT(1, "短整型","int2"),
    /**
     * <code>INTEGER</code>
     * <p>the Integer value type field.</p>
     */
    INTEGER(2, "整型","int4"),
    /**
     * <code>LONG</code>
     * <p>the Long value type field.</p>
     */
    LONG(3, "长整型","int8"),
    /**
     * <code>FLOAT</code>
     * <p>the Float value type field.</p>
     */
    FLOAT(4, "单精度浮点类型","float4"),
    /**
     * <code>DOUBLE</code>
     * <p>the Double value type field.</p>
     */
    DOUBLE(5, "双精度浮点类型","float8"),
    /**
     * <code>TEXT</code>
     * <p>the Text value type field.</p>
     */
    TEXT(6, "文本类型","text"),
    /**
     * <code>DATE</code>
     * <p>the Date value type field.</p>
     */
    DATE(7, "日期类型","timestamp"),
    /**
     * <code>BOOLEAN</code>
     * <p>the Boolean value type field.</p>
     */
    BOOLEAN(8, "布尔类型","bool"),
    /**
     * <code>STRING</code>
     * <p>the String value type field.</p>
     */
    STRING(9, "字符串类型","varchar"),
            ;
    private final Integer key;
    private final String value;
    private final String field;

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
     * <p>the key method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>the key return object is <code>ValueType</code> type.</p>
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
     * <p>the value method.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>the value return object is <code>ValueType</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public static ValueType parseValue(@NonNull String value) {
        ValueType typeEnum = RestValue.parseValue(ValueType.class, value);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    /**
     * <code>parseField</code>
     * <p>the field method.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>the field return object is <code>ValueType</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public static ValueType parseField(@NonNull String field) {
        ValueType typeEnum = RestField.parseField(ValueType.class, field);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    /**
     * <code>isPresent</code>
     * <p>the present method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return boolean <p>the present return object is <code>boolean</code> type.</p>
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
     * <p>the contrast method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return boolean <p>the contrast return object is <code>boolean</code> type.</p>
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
     * <p>the range method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return boolean <p>the range return object is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     */
    public static boolean isRange(Integer key) {
        return isContrast(key);
    }

    /**
     * <code>isEqual</code>
     * <p>the equal method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return boolean <p>the equal return object is <code>boolean</code> type.</p>
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
     * <p>the contain method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return boolean <p>the contain return object is <code>boolean</code> type.</p>
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
