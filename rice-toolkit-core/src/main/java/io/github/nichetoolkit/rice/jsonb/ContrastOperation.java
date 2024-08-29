package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

public enum ContrastOperation implements RestField {
    EQUAL_OPERATION(1, "相等","target = values"),
    GREATER_OPERATION(2, "大于","target > values"),
    GREATER_EQUAL_OPERATION(3, "大于等于","target >= values"),
    LESS_OPERATION(4, "小于","target < values"),
    LESS_EQUAL_OPERATION(5, "小于等于","target <= values"),
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
    public static ContrastOperation parseKey(Integer key) {
        ContrastOperation typeEnum = RestValue.parseKey(ContrastOperation.class, key);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }

    public static ContrastOperation parseValue(String value) {
        ContrastOperation typeEnum = RestValue.parseValue(ContrastOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }

    public static ContrastOperation parseField(String field) {
        ContrastOperation typeEnum = RestField.parseField(ContrastOperation.class, field);
        return Optional.ofNullable(typeEnum).orElse(ContrastOperation.EQUAL_OPERATION);
    }
}
