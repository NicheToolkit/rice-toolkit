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
    public Integer getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public String getField() {
        return this.field;
    }

    public String translateSql(String target, Object startValue, Object endValue) {
        return this.field.replace(TARGET, target)
                .replace(START, String.valueOf(startValue))
                .replace(END, String.valueOf(endValue));
    }

    @JsonCreator
    public static RangeOperation parserKey(@NonNull Integer key) {
        RangeOperation typeEnum = RestValue.parserKey(RangeOperation.class, key);
        return Optional.ofNullable(typeEnum).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
    }

    public static RangeOperation parserValue(@NonNull String value) {
        RangeOperation typeEnum = RestValue.parserValue(RangeOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
    }

    public static RangeOperation parserField(@NonNull String field) {
        RangeOperation typeEnum = RestField.parserField(RangeOperation.class, field);
        return Optional.ofNullable(typeEnum).orElse(RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION);
    }
}
