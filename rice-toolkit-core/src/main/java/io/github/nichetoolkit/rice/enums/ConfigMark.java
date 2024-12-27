package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;

import java.util.Optional;

/**
 * <code>ConfigMark</code>
 * <p>The config mark enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestKey
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum ConfigMark implements RestKey<Class<?>> {
    /**
     * <code>NUMBER</code>
     * <p>The number config mark field.</p>
     */
    NUMBER(Number.class),
    /**
     * <code>STRING</code>
     * <p>The string config mark field.</p>
     */
    STRING(CharSequence.class),
    /**
     * <code>BOOLEAN</code>
     * <p>The boolean config mark field.</p>
     */
    BOOLEAN(Boolean.class),
    ;

    /**
     * <code>NUMBER_UNMARK</code>
     * {@link java.lang.Number} <p>The constant <code>NUMBER_UNMARK</code> field.</p>
     * @see  java.lang.Number
     */
    public static final Number NUMBER_UNMARK = 1;
    /**
     * <code>NUMBER_MARK</code>
     * {@link java.lang.Number} <p>The constant <code>NUMBER_MARK</code> field.</p>
     * @see  java.lang.Number
     */
    public static final Number NUMBER_MARK = 2;
    /**
     * <code>STRING_UNMARK</code>
     * {@link java.lang.CharSequence} <p>The constant <code>STRING_UNMARK</code> field.</p>
     * @see  java.lang.CharSequence
     */
    public static final CharSequence STRING_UNMARK = "normal";
    /**
     * <code>STRING_MARK</code>
     * {@link java.lang.CharSequence} <p>The constant <code>STRING_MARK</code> field.</p>
     * @see  java.lang.CharSequence
     */
    public static final CharSequence STRING_MARK = "delete";
    /**
     * <code>BOOLEAN_UNMARK</code>
     * {@link java.lang.Boolean} <p>The constant <code>BOOLEAN_UNMARK</code> field.</p>
     * @see  java.lang.Boolean
     */
    public static final Boolean BOOLEAN_UNMARK = false;
    /**
     * <code>BOOLEAN_MARK</code>
     * {@link java.lang.Boolean} <p>The constant <code>BOOLEAN_MARK</code> field.</p>
     * @see  java.lang.Boolean
     */
    public static final Boolean BOOLEAN_MARK = true;

    /**
     * <code>key</code>
     * {@link java.lang.Class} <p>The <code>key</code> field.</p>
     * @see  java.lang.Class
     */
    private final Class<?> key;

    /**
     * <code>ConfigMark</code>
     * <p>Instantiates a new config mark.</p>
     * @param key {@link java.lang.Class} <p>The key parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    ConfigMark(Class<?> key) {
        this.key = key;
    }

    @JsonValue
    @Override
    public Class<?> getKey() {
        return this.key;
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.Class} <p>The key parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  com.fasterxml.jackson.annotation.JsonCreator
     * @return  {@link io.github.nichetoolkit.rice.enums.ConfigMark} <p>The parse key return object is <code>ConfigMark</code> type.</p>
     */
    @JsonCreator
    public static ConfigMark parseKey(Class<?> key) {
        ConfigMark typeEnum = RestKey.parseKey(ConfigMark.class, key);
        return Optional.ofNullable(typeEnum).orElse(ConfigMark.NUMBER);
    }

}
