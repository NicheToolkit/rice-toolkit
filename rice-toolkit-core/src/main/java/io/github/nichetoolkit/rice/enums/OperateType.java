package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

public enum OperateType implements RestValue<Integer,String> {
    NONE(1,"无操作"),
    INSERT(2,"新增"),
    UPDATE(4,"更新"),
    COPY(8,"复制"),
    REMOVE(16,"移除"),
    DELETE(32,"删除"),
    ;

    private final Integer key;
    private final String value;

    OperateType(Integer key, String value) {
        this.key = key;
        this.value = value;
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

    @JsonCreator
    public static OperateType parseKey(Integer key) {
        OperateType typeEnum = RestValue.parseKey(OperateType.class, key);
        return Optional.ofNullable(typeEnum).orElse(OperateType.NONE);
    }

    public static OperateType parseValue(String value) {
        OperateType typeEnum = RestValue.parseValue(OperateType.class, value);
        return Optional.ofNullable(typeEnum).orElse(OperateType.NONE);
    }
}
