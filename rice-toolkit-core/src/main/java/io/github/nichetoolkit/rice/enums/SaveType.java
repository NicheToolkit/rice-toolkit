package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>SaveType</code>
 * <p>The type save type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum SaveType implements RestValue<Integer,String> {
    /**
     * <code>NONE</code>
     * <p>The none save type field.</p>
     */
    NONE(1,"无操作"),
    /**
     * <code>CREATE</code>
     * <p>The create save type field.</p>
     */
    CREATE(2,"创建"),
    /**
     * <code>UPDATE</code>
     * <p>The update save type field.</p>
     */
    UPDATE(4,"更新"),
    /**
     * <code>COPY</code>
     * <p>The copy save type field.</p>
     */
    COPY(8,"复制"),
    /**
     * <code>REMOVE</code>
     * <p>The remove save type field.</p>
     */
    REMOVE(16,"移除"),
    /**
     * <code>DELETE</code>
     * <p>The delete save type field.</p>
     */
    DELETE(32,"删除"),
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
     * <code>SaveType</code>
     * <p>Instantiates a new save type.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    SaveType(Integer key, String value) {
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
     * @return {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The key return object is <code>SaveType</code> type.</p>
     * @see java.lang.Integer
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static SaveType parseKey(Integer key) {
        SaveType typeEnum = RestValue.parseKey(SaveType.class, key);
        return Optional.ofNullable(typeEnum).orElse(SaveType.NONE);
    }

    /**
     * <code>parseValue</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The value return object is <code>SaveType</code> type.</p>
     * @see java.lang.String
     */
    public static SaveType parseValue(String value) {
        SaveType typeEnum = RestValue.parseValue(SaveType.class, value);
        return Optional.ofNullable(typeEnum).orElse(SaveType.NONE);
    }
}
