package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <p>SortType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum SortType implements RestValue<String,String> {
    ASC("ASC","升序"),
    DESC("DESC","降序")
    ;

    private final String key;
    private final String value;

    SortType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static SortType parseKey(String key) {
        SortType sortTypeEnum = RestValue.parseKey(SortType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(SortType.DESC);
    }

    public static SortType parseValue(String value) {
        SortType sortTypeEnum = RestValue.parseValue(SortType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(SortType.DESC);
    }


}
