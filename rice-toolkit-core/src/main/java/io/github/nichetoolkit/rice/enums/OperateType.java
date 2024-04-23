package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <p>OperateType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum OperateType implements RestValue<Integer,String> {
    /** 无操作 */
    NONE(1,"无操作"),
    /** 新增 */
    INSERT(2,"新增"),
    /** 更新 */
    UPDATE(4,"更新"),
    /** 复制 逻辑删除 */
    COPY(8,"复制"),
    /** 移除 逻辑删除 */
    REMOVE(16,"移除"),
    /** 删除 物理删除 */
    DELETE(32,"删除"),
    ;

    private final Integer key;
    private final String value;

    OperateType(Integer key, String value) {
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
    public static OperateType parseKey(Integer key) {
        OperateType typeEnum = RestValue.parseKey(OperateType.class, key);
        return Optional.ofNullable(typeEnum).orElse(OperateType.NONE);
    }

    public static OperateType parseValue(String value) {
        OperateType typeEnum = RestValue.parseValue(OperateType.class, value);
        return Optional.ofNullable(typeEnum).orElse(OperateType.NONE);
    }
}
