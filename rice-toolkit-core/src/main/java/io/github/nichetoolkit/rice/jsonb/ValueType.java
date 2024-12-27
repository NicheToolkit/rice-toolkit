package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestStamp;
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
 * <p>The value type enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestStamp
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum ValueType implements RestStamp {
    /**
     * <code>SHORT</code>
     * <p>The short value type field.</p>
     */
    SHORT(1, "smallint","int2"),
    /**
     * <code>INTEGER</code>
     * <p>The integer value type field.</p>
     */
    INTEGER(2, "integer","int4"),
    /**
     * <code>LONG</code>
     * <p>The long value type field.</p>
     */
    LONG(3, "bigint","int8"),
    /**
     * <code>FLOAT</code>
     * <p>The float value type field.</p>
     */
    FLOAT(4, "real","float4"),
    /**
     * <code>DOUBLE</code>
     * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The <code>DOUBLE</code> field.</p>
     */
    DOUBLE(5, "double precision","float8"),
    /**
     * <code>TEXT</code>
     * <p>The text value type field.</p>
     */
    TEXT(6, "text","text"),
    /**
     * <code>DATE</code>
     * <p>The date value type field.</p>
     */
    DATE(7, "timestamptz","timestamp"),
    /**
     * <code>BOOLEAN</code>
     * <p>The boolean value type field.</p>
     */
    BOOLEAN(8, "boolean","bool"),
    /**
     * <code>STRING</code>
     * <p>The string value type field.</p>
     */
    STRING(9, "character","varchar"),
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
     * <code>stamp</code>
     * {@link java.lang.String} <p>The <code>stamp</code> field.</p>
     * @see  java.lang.String
     */
    private final String stamp;

    /**
     * <code>ValueType</code>
     * <p>Instantiates a new value type.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param stamp {@link java.lang.String} <p>The stamp parameter is <code>String</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.String
     */
    ValueType(Integer key, String value, String stamp) {
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
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @see  org.springframework.lang.NonNull
     * @see  com.fasterxml.jackson.annotation.JsonCreator
     * @return  {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The parse key return object is <code>ValueType</code> type.</p>
     */
    @JsonCreator
    public static ValueType parseKey(@NonNull Integer key) {
        ValueType typeEnum = RestValue.parseKey(ValueType.class, key);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @return  {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The parse value return object is <code>ValueType</code> type.</p>
     */
    public static ValueType parseValue(@NonNull String value) {
        ValueType typeEnum = RestValue.parseValue(ValueType.class, value);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    /**
     * <code>parseStamp</code>
     * <p>The parse stamp method.</p>
     * @param stamp {@link java.lang.String} <p>The stamp parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @return  {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The parse stamp return object is <code>ValueType</code> type.</p>
     */
    public static ValueType parseStamp(@NonNull String stamp) {
        ValueType typeEnum = RestStamp.parseStamp(ValueType.class, stamp);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    /**
     * <code>isPresent</code>
     * <p>The is present method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @return boolean <p>The is present return object is <code>boolean</code> type.</p>
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
     * <p>The is contrast method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @return boolean <p>The is contrast return object is <code>boolean</code> type.</p>
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
     * <p>The is range method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @return boolean <p>The is range return object is <code>boolean</code> type.</p>
     */
    public static boolean isRange(Integer key) {
        return isContrast(key);
    }

    /**
     * <code>isEqual</code>
     * <p>The is equal method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @return boolean <p>The is equal return object is <code>boolean</code> type.</p>
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
     * <p>The is contain method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @return boolean <p>The is contain return object is <code>boolean</code> type.</p>
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
