package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Date;
import java.util.Optional;

/**
 * <code>AutoMark</code>
 * <p>The auto mark enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestKey
 * @since Jdk1.8
 */
public enum AutoMark implements RestKey<Class<?>> {
    /**
     * <code>IDENTITY</code>
     * <p>The identity auto mark field.</p>
     */
    IDENTITY(String.class),
    /**
     * <code>DATETIME</code>
     * <p>The datetime auto mark field.</p>
     */
    DATETIME(Date.class),
    /**
     * <code>VERSION</code>
     * <p>The version auto mark field.</p>
     */
    VERSION(Long.class),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.Class} <p>The <code>key</code> field.</p>
     * @see java.lang.Class
     */
    private final Class<?> key;

    /**
     * <code>AutoMark</code>
     * <p>Instantiates a new auto mark.</p>
     * @param key {@link java.lang.Class} <p>The key parameter is <code>Class</code> type.</p>
     * @see java.lang.Class
     */
    AutoMark(Class<?> key) {
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
     * @return {@link io.github.nichetoolkit.rice.enums.AutoMark} <p>The parse key return object is <code>AutoMark</code> type.</p>
     * @see java.lang.Class
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static AutoMark parseKey(Class<?> key) {
        AutoMark typeEnum = RestKey.parseKey(AutoMark.class, key);
        return Optional.ofNullable(typeEnum).orElse(AutoMark.IDENTITY);
    }
}
