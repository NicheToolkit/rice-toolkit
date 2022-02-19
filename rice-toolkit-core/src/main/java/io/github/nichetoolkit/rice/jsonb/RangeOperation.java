package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <p>RangeOperation</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public enum RangeOperation implements RestField {
    /** 大于等于且小于等于 */
    GREATER_EQUAL_LESS_EQUAL_OPERATION(1, "大于等于且小于等于","(target >= start and target <= end)"),
    /** 大于等于且小于 */
    GREATER_EQUAL_LESS_OPERATION(2, "大于等于且小于","(target >= start and target < end)"),
    /** 大于且小于等于 */
    GREATER_LESS_EQUAL_OPERATION(3, "大于且小于等于","(target > start and target <= end)"),
    /** 大于且小于 */
    GREATER_LESS_OPERATION(4, "大于且小于","(target > start and target < end)"),
    ;

    private final Integer key;
    private final String value;
    private final String field;

    public static final String TARGET = "target";
    public static final String START = "start";
    public static final String END = "end";

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

    public String translateSql(String target, Object startValue, Object endValue) {
        return this.field.replace(TARGET, target)
                .replace(START, String.valueOf(startValue))
                .replace(END, String.valueOf(endValue));
    }

    @JsonCreator
    public static RangeOperation parseKey(@NonNull Integer key) {
        RangeOperation typeEnum = RestValue.parseKey(RangeOperation.class, key);
        return Optional.ofNullable(typeEnum).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
    }

    public static RangeOperation parseValue(@NonNull String value) {
        RangeOperation typeEnum = RestValue.parseValue(RangeOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
    }

    public static RangeOperation parseField(@NonNull String field) {
        RangeOperation typeEnum = RestField.parseField(RangeOperation.class, field);
        return Optional.ofNullable(typeEnum).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
    }
}
