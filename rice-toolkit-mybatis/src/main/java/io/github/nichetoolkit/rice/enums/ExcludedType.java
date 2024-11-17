package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rice.consts.SQLConstants;
import io.github.nichetoolkit.rest.RestKey;

import java.util.Optional;

/**
 * <code>ExcludedType</code>
 * <p>The excluded type enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestKey
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum ExcludedType implements RestKey<String> {
    /**
     * <code>EXCLUDED</code>
     * <p>The excluded excluded type field.</p>
     */
    EXCLUDED(SQLConstants.EXCLUDED),
    /**
     * <code>VALUES</code>
     * <p>The values excluded type field.</p>
     */
    VALUES(SQLConstants.VALUES),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.String} <p>The <code>key</code> field.</p>
     * @see  java.lang.String
     */
    private final String key;

    /**
     * <code>ExcludedType</code>
     * <p>Instantiates a new excluded type.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    ExcludedType(String key) {
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
     * @return  {@link io.github.nichetoolkit.rice.enums.ExcludedType} <p>The parse key return object is <code>ExcludedType</code> type.</p>
     */
    @JsonCreator
    public static ExcludedType parseKey(String key) {
        ExcludedType typeEnum = RestKey.parseKey(ExcludedType.class, key);
        return Optional.ofNullable(typeEnum).orElse(ExcludedType.EXCLUDED);
    }

}
