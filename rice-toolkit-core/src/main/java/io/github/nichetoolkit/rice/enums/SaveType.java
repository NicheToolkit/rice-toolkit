package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

public enum SaveType implements RestValue<Integer,String> {
    NONE(1,"无操作"),
    CREATE(2,"创建"),
    UPDATE(4,"更新"),
    COPY(8,"复制"),
    REMOVE(16,"移除"),
    DELETE(32,"删除"),
    ;

    private final Integer key;
    private final String value;

    SaveType(Integer key, String value) {
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
    public static SaveType parseKey(Integer key) {
        SaveType typeEnum = RestValue.parseKey(SaveType.class, key);
        return Optional.ofNullable(typeEnum).orElse(SaveType.NONE);
    }

    public static SaveType parseValue(String value) {
        SaveType typeEnum = RestValue.parseValue(SaveType.class, value);
        return Optional.ofNullable(typeEnum).orElse(SaveType.NONE);
    }
}
