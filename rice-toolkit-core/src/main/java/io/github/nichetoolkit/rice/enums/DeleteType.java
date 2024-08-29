package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

public enum DeleteType implements RestValue<Integer,String> {
    NONE(0,"none"),
    REMOVE(1,"remove"),
    DELETE(2,"delete"),
    OPERATE(3,"operate"),
    ;

    private final Integer key;
    private final String value;

    DeleteType(Integer key,String value) {
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
    public static DeleteType parseKey(Integer key) {
        DeleteType typeEnum = RestValue.parseKey(DeleteType.class, key);
        return Optional.ofNullable(typeEnum).orElse(DeleteType.REMOVE);
    }

    public static DeleteType parseValue(String value) {
        DeleteType typeEnum = RestValue.parseValue(DeleteType.class, value);
        return Optional.ofNullable(typeEnum).orElse(DeleteType.REMOVE);
    }



}
