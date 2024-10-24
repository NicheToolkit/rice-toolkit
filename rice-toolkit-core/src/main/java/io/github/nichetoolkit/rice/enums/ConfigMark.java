package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;

import java.util.Optional;

public enum ConfigMark implements RestKey<Class<?>> {
    NUMBER(Number.class),
    STRING(CharSequence.class),
    BOOLEAN(Boolean.class),
    ;

    public static final Number NUMBER_UNMARK = 1;
    public static final Number NUMBER_MARK = 2;
    public static final CharSequence STRING_UNMARK = "normal";
    public static final CharSequence STRING_MARK = "delete";
    public static final Boolean BOOLEAN_UNMARK = false;
    public static final Boolean BOOLEAN_MARK = true;

    private final Class<?> key;

    ConfigMark(Class<?> key) {
        this.key = key;
    }

    @JsonValue
    @Override
    public Class<?> getKey() {
        return this.key;
    }

    @JsonCreator
    public static ConfigMark parseKey(Class<?> key) {
        ConfigMark typeEnum = RestKey.parseKey(ConfigMark.class, key);
        return Optional.ofNullable(typeEnum).orElse(ConfigMark.NUMBER);
    }

}
