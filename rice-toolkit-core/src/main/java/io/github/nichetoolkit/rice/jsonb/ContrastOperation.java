package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <p>ContrastOperation</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum ContrastOperation implements RestField {
    /** 相等 */
    EQUAL_OPERATION(1, "相等","target = values"),
    /** 大于 */
    GREATER_OPERATION(2, "大于","target > values"),
    /** 大于等于 */
    GREATER_EQUAL_OPERATION(3, "大于等于","target >= values"),
    /** 小于 */
    LESS_OPERATION(4, "小于","target < values"),
    /** 小于等于 */
    LESS_EQUAL_OPERATION(5, "小于等于","target <= values"),
    /** 不等于 */
    UNEQUAL_OPERATION(6, "不等于","target != values"),
    ;
    private final Integer key;
    private final String value;
    private final String field;

    public static final String TARGET = "target";
    public static final String VALUE = "values";

    ContrastOperation(Integer key, String value, String field) {
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
    
    public String translateSql(String target, Object value) {
       return this.field.replace(TARGET, target).replace(VALUE, String.valueOf(value));
    }

    @JsonCreator
    public static ContrastOperation parserKey(@NonNull Integer key) {
        ContrastOperation typeEnum = RestValue.parserKey(ContrastOperation.class, key);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }

    public static ContrastOperation parserValue(@NonNull String value) {
        ContrastOperation typeEnum = RestValue.parserValue(ContrastOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }

    public static ContrastOperation parserField(@NonNull String field) {
        ContrastOperation typeEnum = RestField.parserField(ContrastOperation.class, field);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }
}
