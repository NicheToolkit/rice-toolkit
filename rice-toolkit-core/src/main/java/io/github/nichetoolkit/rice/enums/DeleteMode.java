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
     * <p>the None delete mode field.</p>
     */
    NONE(0,"none"),
    /**
     * <code>REMOVE</code>
     * <p>the Remove delete mode field.</p>
     */
    REMOVE(1,"remove"),
    /**
     * <code>DELETE</code>
     * <p>the Delete delete mode field.</p>
     */
    DELETE(2,"delete"),
    /**
     * <code>OPERATE</code>
     * <p>the Operate delete mode field.</p>
     */
    OPERATE(3,"operate"),
    ;

    private final Integer key;
    private final String value;

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
     * <p>the key method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>the key return object is <code>DeleteMode</code> type.</p>
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
     * <p>the value method.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>the value return object is <code>DeleteMode</code> type.</p>
     * @see java.lang.String
     */
    public static DeleteMode parseValue(String value) {
        DeleteMode typeEnum = RestValue.parseValue(DeleteMode.class, value);
        return Optional.ofNullable(typeEnum).orElse(DeleteMode.REMOVE);
    }



}
