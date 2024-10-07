package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>DeleteMode</code>
 * <p>The type delete mode enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum DeleteMode implements RestValue<Integer,String> {
    /**
     * <code>NONE</code>
     * <p>The none delete mode field.</p>
     */
    NONE(0,"none"),
    /**
     * <code>REMOVE</code>
     * <p>The remove delete mode field.</p>
     */
    REMOVE(1,"remove"),
    /**
     * <code>DELETE</code>
     * <p>The delete delete mode field.</p>
     */
    DELETE(2,"delete"),
    /**
     * <code>OPERATE</code>
     * <p>The operate delete mode field.</p>
     */
    OPERATE(3,"operate"),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>The <code>key</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see java.lang.String
     */
    private final String value;

    /**
     * <code>DeleteMode</code>
     * <p>Instantiates a new delete mode.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    DeleteMode(Integer key, String value) {
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

    /**
     * <code>parseKey</code>
     * <p>The key method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>The key return object is <code>DeleteMode</code> type.</p>
     * @see java.lang.Integer
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static DeleteMode parseKey(Integer key) {
        DeleteMode typeEnum = RestValue.parseKey(DeleteMode.class, key);
        return Optional.ofNullable(typeEnum).orElse(DeleteMode.REMOVE);
    }

    /**
     * <code>parseValue</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>The value return object is <code>DeleteMode</code> type.</p>
     * @see java.lang.String
     */
    public static DeleteMode parseValue(String value) {
        DeleteMode typeEnum = RestValue.parseValue(DeleteMode.class, value);
        return Optional.ofNullable(typeEnum).orElse(DeleteMode.REMOVE);
    }



}
