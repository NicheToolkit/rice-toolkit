package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestArithmetic;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <p>ActorType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum ActorType implements RestArithmetic {
    /** 未知角色 */
    UNKNOWN(0L,"未知角色",0L),
    /** 超级管理员 */
    SUPER(1L,"超级管理员",1L),
    /** 管理员 */
    ADMIN(2L,"管理员",2L),
    /** 领导者 */
    LEADER(4L,"领导",4L),
    /** 组长 */
    GROUP(8L,"组长",8L),
    /** 用户 */
    USER(16L,"用户",16L),
    ;

    private final Long key;
    private final String value;
    private final Long arithmetic;

    ActorType(Long key, String value, Long arithmetic) {
        this.key = key;
        this.value = value;
        this.arithmetic = arithmetic;
    }

    @JsonValue
    @Override
    public Long getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Long getArithmetic() {
        return this.arithmetic;
    }


    @JsonCreator
    public static ActorType parseKey(Long key) {
        ActorType sortTypeEnum = RestValue.parseKey(ActorType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(ActorType.UNKNOWN);
    }

    public static ActorType parseValue(String value) {
        ActorType sortTypeEnum = RestValue.parseValue(ActorType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(ActorType.UNKNOWN);
    }

    public static ActorType parseArithmetic(Long arithmetic) {
        ActorType typeEnum = RestArithmetic.parseArithmetic(ActorType.class, arithmetic);
        return Optional.ofNullable(typeEnum).orElse(ActorType.UNKNOWN);
    }
}
