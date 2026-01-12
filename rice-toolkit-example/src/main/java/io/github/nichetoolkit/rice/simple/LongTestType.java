package io.github.nichetoolkit.rice.simple;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

public enum LongTestType implements RestKey<Long> {
    TEST_ALL(0L),
    TEST_1(1L),
    TEST_2( 2L),
    TEST_3(4L),
    ;

    private final Long key;

    LongTestType(Long key) {
        this.key = key;
    }

    @JsonValue
    @Override
    public Long getKey() {
        return this.key;
    }

    @JsonCreator
    public static LongTestType parseKey(Long key) {
        LongTestType typeEnum = RestKey.parseKey(LongTestType.class, key);
        return Optional.ofNullable(typeEnum).orElse(LongTestType.TEST_ALL);
    }

}
