package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rice.consts.StyleConstants;
import io.github.nichetoolkit.rest.RestKey;

import java.util.Optional;

/**
 * <code>StyleType</code>
 * <p>The style type enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestKey
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum StyleType implements RestKey<String> {
    /**
     * <code>NORMAL</code>
     * <p>The normal style type field.</p>
     */
    NORMAL(StyleConstants.NORMAL),
    /**
     * <code>LOWER_UNDERLINE</code>
     * <p>The lower underline style type field.</p>
     */
    LOWER_UNDERLINE(StyleConstants.LOWER_UNDERLINE),
    /**
     * <code>LOWER</code>
     * <p>The lower style type field.</p>
     */
    LOWER(StyleConstants.LOWER),
    /**
     * <code>UPPER</code>
     * <p>The upper style type field.</p>
     */
    UPPER(StyleConstants.UPPER),
    /**
     * <code>UPPER_UNDERLINE</code>
     * <p>The upper underline style type field.</p>
     */
    UPPER_UNDERLINE(StyleConstants.UPPER_UNDERLINE),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.String} <p>The <code>key</code> field.</p>
     * @see  java.lang.String
     */
    private final String key;

    /**
     * <code>StyleType</code>
     * <p>Instantiates a new style type.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    StyleType(String key) {
        this.key = key;
    }

    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.annotation.JsonCreator
     * @return  {@link io.github.nichetoolkit.rice.enums.StyleType} <p>The parse key return object is <code>StyleType</code> type.</p>
     */
    @JsonCreator
    public static StyleType parseKey(String key) {
        StyleType typeEnum = RestKey.parseKey(StyleType.class, key);
        return Optional.ofNullable(typeEnum).orElse(StyleType.NORMAL);
    }

}
