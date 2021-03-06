package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <p>EqualOperation</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public enum EqualOperation implements RestField {
    /** 相等 */
    EQUAL_OPERATION(1, "相等","target = 'values'"),
    /** 左模糊 */
    LEFT_LIKE_OPERATION(2, "左模糊","target like concat('%','values')"),
    /** 右模糊 */
    RIGHT_LIKE_OPERATION(3, "右模糊","target like concat('values','%')"),
    /** 全模糊 */
    ALL_LIKE_OPERATION(4, "全模糊","target like concat('%','values','%')"),
    /** 不为空 */
    NOT_NULL_OPERATION(5, "不为空","target is not null"),
    ;

    private final Integer key;
    private final String value;
    private final String field;

    public static final String TARGET = "target";
    public static final String VALUE = "values";

    EqualOperation(Integer key, String value, String field) {
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

    public String translateSql(String target, Object value) {
        return this.field.replace(TARGET, target).replace(VALUE, String.valueOf(value));
    }

    @JsonCreator
    public static EqualOperation parseKey(Integer key) {
        EqualOperation typeEnum = RestValue.parseKey(EqualOperation.class, key);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }

    public static EqualOperation parseValue(String value) {
        EqualOperation typeEnum = RestValue.parseValue(EqualOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }

    public static EqualOperation parseField(String field) {
        EqualOperation typeEnum = RestField.parseField(EqualOperation.class, field);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }
}
