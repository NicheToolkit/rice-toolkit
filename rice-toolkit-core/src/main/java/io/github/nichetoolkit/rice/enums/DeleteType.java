package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <p>DeleteType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum DeleteType implements RestValue<Integer,String> {
    /** 移除 逻辑删除 */
    REMOVE(1,"remove"),
    /** 删除 物理删除  */
    DELETE(2,"delete"),
    /** 复制 逻辑删除 */
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
        DeleteType sortTypeEnum = RestValue.parseKey(DeleteType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(DeleteType.REMOVE);
    }

    public static DeleteType parseValue(String value) {
        DeleteType sortTypeEnum = RestValue.parseValue(DeleteType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(DeleteType.REMOVE);
    }



}
