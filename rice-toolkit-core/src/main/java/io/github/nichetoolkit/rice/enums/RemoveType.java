package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

public enum RemoveType implements RestValue<Integer, String> {
    BOOLEAN(1, "boolean"),
    NUMBER(2, "number"),
    DATETIME(3, "datetime"),
    IDENTITY(4, "identity"),
    BACKUP(5, "backup"),
    ;

    private final Integer key;
    private final String value;

    RemoveType(Integer key, String value) {
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
    public static RemoveType parseKey(Integer key) {
        RemoveType typeEnum = RestValue.parseKey(RemoveType.class, key);
        return Optional.ofNullable(typeEnum).orElse(RemoveType.BOOLEAN);
    }

    public static RemoveType parseValue(String value) {
        RemoveType typeEnum = RestValue.parseValue(RemoveType.class, value);
        return Optional.ofNullable(typeEnum).orElse(RemoveType.BOOLEAN);
    }

    public static String sign(RemoveType removeModel, boolean booleanSign, Integer numberSign) {
        if (removeModel == RemoveType.BOOLEAN) {
            return String.valueOf(booleanSign);
        } else if (removeModel == RemoveType.NUMBER) {
            return String.valueOf(numberSign);
        } else if (removeModel == RemoveType.DATETIME) {
            return "now()";
        } else if (removeModel == RemoveType.IDENTITY) {
            return "id";
        } else {
            return null;
        }
    }

    public static String value(RemoveType removeModel, boolean booleanValue, Integer numberValue) {
        if (removeModel == RemoveType.BOOLEAN) {
            return String.valueOf(booleanValue);
        } else if (removeModel == RemoveType.NUMBER) {
            return String.valueOf(numberValue);
        } else {
            return null;
        }
    }
}
