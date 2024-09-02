package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>RemoveType</code>
 * <p>The type remove type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum RemoveType implements RestValue<Integer, String> {
    /**
     * <code>BOOLEAN</code>
     * <p>the Boolean remove type field.</p>
     */
    BOOLEAN(1, "boolean"),
    /**
     * <code>NUMBER</code>
     * <p>the Number remove type field.</p>
     */
    NUMBER(2, "number"),
    /**
     * <code>DATETIME</code>
     * <p>the Datetime remove type field.</p>
     */
    DATETIME(3, "datetime"),
    /**
     * <code>IDENTITY</code>
     * <p>the Identity remove type field.</p>
     */
    IDENTITY(4, "identity"),
    /**
     * <code>BACKUP</code>
     * <p>the Backup remove type field.</p>
     */
    BACKUP(5, "backup"),
    ;

    private final Integer key;
    private final String value;

    RemoveType(Integer key, String value) {
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
     * @return {@link io.github.nichetoolkit.rice.enums.RemoveType} <p>the key return object is <code>RemoveType</code> type.</p>
     * @see java.lang.Integer
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static RemoveType parseKey(Integer key) {
        RemoveType typeEnum = RestValue.parseKey(RemoveType.class, key);
        return Optional.ofNullable(typeEnum).orElse(RemoveType.BOOLEAN);
    }

    /**
     * <code>parseValue</code>
     * <p>the value method.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.RemoveType} <p>the value return object is <code>RemoveType</code> type.</p>
     * @see java.lang.String
     */
    public static RemoveType parseValue(String value) {
        RemoveType typeEnum = RestValue.parseValue(RemoveType.class, value);
        return Optional.ofNullable(typeEnum).orElse(RemoveType.BOOLEAN);
    }

    /**
     * <code>sign</code>
     * <p>the method.</p>
     * @param removeModel {@link io.github.nichetoolkit.rice.enums.RemoveType} <p>the remove model parameter is <code>RemoveType</code> type.</p>
     * @param booleanSign boolean <p>the boolean sign parameter is <code>boolean</code> type.</p>
     * @param numberSign  {@link java.lang.Integer} <p>the number sign parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public static String sign(RemoveType removeModel, boolean booleanSign, Integer numberSign) {
        if (removeModel == RemoveType.BOOLEAN) {
            return String.valueOf(booleanSign);
        } else if (removeModel == RemoveType.NUMBER) {
            return String.valueOf(numberSign);
        } else if (removeModel == RemoveType.DATETIME) {
            return "now()";
        } else if (removeModel == RemoveType.IDENTITY) {
            return "id";
        } else {
            return null;
        }
    }

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @param removeModel  {@link io.github.nichetoolkit.rice.enums.RemoveType} <p>the remove model parameter is <code>RemoveType</code> type.</p>
     * @param booleanValue boolean <p>the boolean value parameter is <code>boolean</code> type.</p>
     * @param numberValue  {@link java.lang.Integer} <p>the number value parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public static String value(RemoveType removeModel, boolean booleanValue, Integer numberValue) {
        if (removeModel == RemoveType.BOOLEAN) {
            return String.valueOf(booleanValue);
        } else if (removeModel == RemoveType.NUMBER) {
            return String.valueOf(numberValue);
        } else {
            return null;
        }
    }
}
