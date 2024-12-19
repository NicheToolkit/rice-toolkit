package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rice.consts.SQLConstants;
import io.github.nichetoolkit.rest.RestKey;

import java.util.Optional;

/**
 * <code>UpsetType</code>
 * <p>The upset type enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestKey
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum UpsetType implements RestKey<String> {
    /**
     * <code>BUILTIN</code>
     * <p>The builtin upset type field.</p>
     */
    BUILTIN(SQLConstants.BUILTIN),
    /**
     * <code>EXCLUDED</code>
     * <p>The excluded upset type field.</p>
     */
    EXCLUDED(SQLConstants.EXCLUDED),
    /**
     * <code>VALUES</code>
     * <p>The values upset type field.</p>
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
     * <code>UpsetType</code>
     * <p>Instantiates a new upset type.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    UpsetType(String key) {
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
     * @return  {@link io.github.nichetoolkit.rice.enums.UpsetType} <p>The parse key return object is <code>UpsetType</code> type.</p>
     */
    @JsonCreator
    public static UpsetType parseKey(String key) {
        UpsetType typeEnum = RestKey.parseKey(UpsetType.class, key);
        return Optional.ofNullable(typeEnum).orElse(UpsetType.BUILTIN);
    }

}
