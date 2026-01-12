package io.github.nichetoolkit.rice.simple;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;

import java.util.Optional;

public enum StringTestType implements RestKey<String> {
    TEST_ALL("all"),
    TEST_1("a"),
    TEST_2( "b"),
    TEST_3("c"),
    ;

    private final String key;

    StringTestType(String key) {
        this.key = key;
    }

    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    @JsonCreator
    public static StringTestType parseKey(String key) {
        StringTestType typeEnum = RestKey.parseKey(StringTestType.class, key);
        return Optional.ofNullable(typeEnum).orElse(StringTestType.TEST_ALL);
    }

}
