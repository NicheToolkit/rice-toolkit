package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>RemoveMode</code>
 * <p>The type remove mode enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum RemoveMode implements RestValue<Integer, String> {
    /**
     * <code>BOOLEAN</code>
     * <p>The boolean remove mode field.</p>
     */
    BOOLEAN(1, "boolean"),
    /**
     * <code>NUMBER</code>
     * <p>The number remove mode field.</p>
     */
    NUMBER(2, "number"),
    /**
     * <code>DATETIME</code>
     * <p>The datetime remove mode field.</p>
     */
    DATETIME(3, "datetime"),
    /**
     * <code>IDENTITY</code>
     * <p>The identity remove mode field.</p>
     */
    IDENTITY(4, "identity"),
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
     * <code>RemoveMode</code>
     * <p>Instantiates a new remove mode.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    RemoveMode(Integer key, String value) {
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
     * @return {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>The key return object is <code>RemoveMode</code> type.</p>
     * @see java.lang.Integer
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static RemoveMode parseKey(Integer key) {
        RemoveMode typeEnum = RestValue.parseKey(RemoveMode.class, key);
        return Optional.ofNullable(typeEnum).orElse(RemoveMode.BOOLEAN);
    }

    /**
     * <code>parseValue</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>The value return object is <code>RemoveMode</code> type.</p>
     * @see java.lang.String
     */
    public static RemoveMode parseValue(String value) {
        RemoveMode typeEnum = RestValue.parseValue(RemoveMode.class, value);
        return Optional.ofNullable(typeEnum).orElse(RemoveMode.BOOLEAN);
    }

    /**
     * <code>sign</code>
     * <p>The method.</p>
     * @param removeModel {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>The remove model parameter is <code>RemoveMode</code> type.</p>
     * @param booleanSign boolean <p>The boolean sign parameter is <code>boolean</code> type.</p>
     * @param numberSign  {@link java.lang.Integer} <p>The number sign parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public static String sign(RemoveMode removeModel, boolean booleanSign, Integer numberSign) {
        if (removeModel == RemoveMode.BOOLEAN) {
            return String.valueOf(booleanSign);
        } else if (removeModel == RemoveMode.NUMBER) {
            return String.valueOf(numberSign);
        } else if (removeModel == RemoveMode.DATETIME) {
            return "now()";
        } else if (removeModel == RemoveMode.IDENTITY) {
            return "id";
        } else {
            return null;
        }
    }

    /**
     * <code>value</code>
     * <p>The method.</p>
     * @param removeModel  {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>The remove model parameter is <code>RemoveMode</code> type.</p>
     * @param booleanValue boolean <p>The boolean value parameter is <code>boolean</code> type.</p>
     * @param numberValue  {@link java.lang.Integer} <p>The number value parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public static String value(RemoveMode removeModel, boolean booleanValue, Integer numberValue) {
        if (removeModel == RemoveMode.BOOLEAN) {
            return String.valueOf(booleanValue);
        } else if (removeModel == RemoveMode.NUMBER) {
            return String.valueOf(numberValue);
        } else {
            return null;
        }
    }
}
