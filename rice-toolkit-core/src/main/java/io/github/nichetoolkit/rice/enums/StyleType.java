package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rice.consts.StyleConst;

import java.util.Optional;

/**
 * <p>StyleType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum StyleType implements RestKey<String> {
    /** 默认驼峰 */
    NORMAL(StyleConst.NORMAL),
    /** 小写加下划线 */
    LOWER_UNDERLINE(StyleConst.LOWER_UNDERLINE),
    /** 小写  */
    LOWER(StyleConst.LOWER),
    /** 大写  */
    UPPER(StyleConst.UPPER),
    /** 大写加下划线  */
    UPPER_UNDERLINE(StyleConst.UPPER_UNDERLINE),
    ;

    private final String key;

    StyleType(String key) {
        this.key = key;
    }

    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    @JsonCreator
    public static StyleType parseKey(String key) {
        StyleType typeEnum = RestKey.parseKey(StyleType.class, key);
        return Optional.ofNullable(typeEnum).orElse(StyleType.NORMAL);
    }

}
