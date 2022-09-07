package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <p>SaveType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum SaveType implements RestValue<Integer,String> {
    NONE(1,"无操作"),
    /** 创建 */
    CREATE(2,"创建"),
    /** 更新 */
    UPDATE(4,"更新")
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
        SaveType sortTypeEnum = RestValue.parseKey(SaveType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(SaveType.NONE);
    }

    public static SaveType parseValue(String value) {
        SaveType sortTypeEnum = RestValue.parseValue(SaveType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(SaveType.NONE);
    }
}
