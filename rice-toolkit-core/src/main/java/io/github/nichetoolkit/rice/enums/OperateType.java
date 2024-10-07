package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>OperateType</code>
 * <p>The type operate type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum OperateType implements RestValue<Integer,String> {
    /**
     * <code>NONE</code>
     * <p>The none operate type field.</p>
     */
    NONE(1,"无操作"),
    /**
     * <code>INSERT</code>
     * <p>The insert operate type field.</p>
     */
    INSERT(2,"新增"),
    /**
     * <code>UPDATE</code>
     * <p>The update operate type field.</p>
     */
    UPDATE(4,"更新"),
    /**
     * <code>COPY</code>
     * <p>The copy operate type field.</p>
     */
    COPY(8,"复制"),
    /**
     * <code>REMOVE</code>
     * <p>The remove operate type field.</p>
     */
    REMOVE(16,"移除"),
    /**
     * <code>DELETE</code>
     * <p>The delete operate type field.</p>
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
     * <code>OperateType</code>
     * <p>Instantiates a new operate type.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    OperateType(Integer key, String value) {
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
     * @return {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The key return object is <code>OperateType</code> type.</p>
     * @see java.lang.Integer
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static OperateType parseKey(Integer key) {
        OperateType typeEnum = RestValue.parseKey(OperateType.class, key);
        return Optional.ofNullable(typeEnum).orElse(OperateType.NONE);
    }

    /**
     * <code>parseValue</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The value return object is <code>OperateType</code> type.</p>
     * @see java.lang.String
     */
    public static OperateType parseValue(String value) {
        OperateType typeEnum = RestValue.parseValue(OperateType.class, value);
        return Optional.ofNullable(typeEnum).orElse(OperateType.NONE);
    }
}
