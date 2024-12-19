package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rice.consts.SQLConstants;
import io.github.nichetoolkit.rest.RestKey;

import java.util.Optional;

public enum ExcludedType implements RestKey<String> {
    BUILTIN(SQLConstants.BUILTIN),
    EXCLUDED(SQLConstants.EXCLUDED),
    VALUES(SQLConstants.VALUES),
    ;

    private final String key;

    ExcludedType(String key) {
        this.key = key;
    }

    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    @JsonCreator
    public static ExcludedType parseKey(String key) {
        ExcludedType typeEnum = RestKey.parseKey(ExcludedType.class, key);
        return Optional.ofNullable(typeEnum).orElse(ExcludedType.BUILTIN);
    }

}
