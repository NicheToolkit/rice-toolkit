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
 * <p>ValueType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum ValueType implements RestField {
    /** 短整型 */
    SHORT(1, "短整型","int2"),
    /** 整型 */
    INTEGER(2, "整型","int4"),
    /** 长整型 */
    LONG(3, "长整型","int8"),
    /** 单精度浮点类型 */
    FLOAT(4, "单精度浮点类型","float4"),
    /** 双精度浮点类型 */
    DOUBLE(5, "双精度浮点类型","float8"),
    /** 文本类型 */
    TEXT(6, "文本类型","text"),
    /** 日期类型 */
    DATE(7, "日期类型","timestamp"),
    /** 布尔类型 */
    BOOLEAN(8, "布尔类型","bool"),
    /** 字符串类型 */
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

    @JsonCreator
    public static ValueType parseKey(@NonNull Integer key) {
        ValueType typeEnum = RestValue.parseKey(ValueType.class, key);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    public static ValueType parseValue(@NonNull String value) {
        ValueType typeEnum = RestValue.parseValue(ValueType.class, value);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    public static ValueType parseField(@NonNull String field) {
        ValueType typeEnum = RestField.parseField(ValueType.class, field);
        return Optional.ofNullable(typeEnum).orElse(ValueType.STRING);
    }

    public static boolean isPresent(Integer key) {
        if (GeneralUtils.isEmpty(key)) {
            return false;
        }
        Map<Integer, ValueType> valueTypeEnums = Stream.of(ValueType.values()).collect(Collectors.toMap(ValueType::getKey, Function.identity()));
        return Optional.ofNullable(valueTypeEnums.get(key)).isPresent();
    }

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

    public static boolean isRange(Integer key) {
        return isContrast(key);
    }

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
