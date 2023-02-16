package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <p>RemoveModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum RemoveType implements RestValue<Integer,String> {
    /** boolean类型 true or false删除 */
    BOOLEAN(1,"boolean"),
    /** 状态数字 删除 */
    NUMBER(2,"number"),
    /** 时间戳方式 now() 标记删除 */
    DATETIME(3,"datetime"),
    /** 主键id标记 逻辑删除 */
    IDENTITY(4,"identity"),
    /** 备份方式 逻辑删除 */
    BACKUP(5,"backup"),
    ;

    private final Integer key;
    private final String value;

    RemoveType(Integer key,String value) {
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

    public static String sign(RemoveType removeModel,boolean booleanSign,Integer numberSign) {
        if (removeModel == RemoveType.BOOLEAN) {
            return String.valueOf(booleanSign);
        } else if(removeModel == RemoveType.NUMBER) {
            return String.valueOf(numberSign);
        }  else if(removeModel == RemoveType.DATETIME) {
            return "now()";
        } else if(removeModel == RemoveType.IDENTITY) {
            return "id";
        } else {
            return null;
        }
    }

    public static String value(RemoveType removeModel, boolean booleanValue,Integer numberValue) {
        if (removeModel == RemoveType.BOOLEAN) {
            return String.valueOf(booleanValue);
        } else if(removeModel == RemoveType.NUMBER) {
            return String.valueOf(numberValue);
        } else {
            return null;
        }
    }
}
