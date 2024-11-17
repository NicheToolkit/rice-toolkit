package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>SortType</code>
 * <p>The sort type enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestValue
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum SortType implements RestValue<String,String> {
    /**
     * <code>NONE</code>
     * <p>The none sort type field.</p>
     */
    NONE("",""),
    /**
     * <code>ASC</code>
     * <p>The asc sort type field.</p>
     */
    ASC("ASC","升序"),
    /**
     * <code>DESC</code>
     * <p>The desc sort type field.</p>
     */
    DESC("DESC","降序")
    ;

    /**
     * <code>key</code>
     * {@link java.lang.String} <p>The <code>key</code> field.</p>
     * @see  java.lang.String
     */
    private final String key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see  java.lang.String
     */
    private final String value;

    /**
     * <code>SortType</code>
     * <p>Instantiates a new sort type.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    SortType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.annotation.JsonCreator
     * @return  {@link io.github.nichetoolkit.rice.enums.SortType} <p>The parse key return object is <code>SortType</code> type.</p>
     */
    @JsonCreator
    public static SortType parseKey(String key) {
        SortType typeEnum = RestValue.parseKey(SortType.class, key);
        return Optional.ofNullable(typeEnum).orElse(SortType.DESC);
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.enums.SortType} <p>The parse value return object is <code>SortType</code> type.</p>
     */
    public static SortType parseValue(String value) {
        SortType typeEnum = RestValue.parseValue(SortType.class, value);
        return Optional.ofNullable(typeEnum).orElse(SortType.DESC);
    }


}
