package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>EqualOperation</code>
 * <p>The type equal operation enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestField
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
public enum EqualOperation implements RestField {
    /**
     * <code>EQUAL_OPERATION</code>
     * <p>the Equal operation equal operation field.</p>
     */
    EQUAL_OPERATION(1, "相等","target = 'values'"),
    /**
     * <code>LEFT_LIKE_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>the <code>LEFT_LIKE_OPERATION</code> field.</p>
     */
    LEFT_LIKE_OPERATION(2, "左模糊","target like concat('%','values')"),
    /**
     * <code>RIGHT_LIKE_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>the <code>RIGHT_LIKE_OPERATION</code> field.</p>
     */
    RIGHT_LIKE_OPERATION(3, "右模糊","target like concat('values','%')"),
    /**
     * <code>ALL_LIKE_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>the <code>ALL_LIKE_OPERATION</code> field.</p>
     */
    ALL_LIKE_OPERATION(4, "全模糊","target like concat('%','values','%')"),
    /**
     * <code>NOT_NULL_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>the <code>NOT_NULL_OPERATION</code> field.</p>
     */
    NOT_NULL_OPERATION(5, "不为空","target is not null"),
    ;

    private final Integer key;
    private final String value;
    private final String field;

    /**
     * <code>TARGET</code>
     * {@link java.lang.String} <p>the constant <code>TARGET</code> field.</p>
     * @see java.lang.String
     */
    public static final String TARGET = "target";
    /**
     * <code>VALUE</code>
     * {@link java.lang.String} <p>the constant <code>VALUE</code> field.</p>
     * @see java.lang.String
     */
    public static final String VALUE = "values";

    EqualOperation(Integer key, String value, String field) {
        this.key = key;
        this.value = value;
        this.field = field;
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

    @Override
    public String getField() {
        return this.field;
    }

    /**
     * <code>translateSql</code>
     * <p>the sql method.</p>
     * @param target {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.String} <p>the sql return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public String translateSql(String target, Object value) {
        return this.field.replace(TARGET, target).replace(VALUE, String.valueOf(value));
    }

    /**
     * <code>parseKey</code>
     * <p>the key method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>the key return object is <code>EqualOperation</code> type.</p>
     * @see java.lang.Integer
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static EqualOperation parseKey(Integer key) {
        EqualOperation typeEnum = RestValue.parseKey(EqualOperation.class, key);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }

    /**
     * <code>parseValue</code>
     * <p>the value method.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>the value return object is <code>EqualOperation</code> type.</p>
     * @see java.lang.String
     */
    public static EqualOperation parseValue(String value) {
        EqualOperation typeEnum = RestValue.parseValue(EqualOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }

    /**
     * <code>parseField</code>
     * <p>the field method.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>the field return object is <code>EqualOperation</code> type.</p>
     * @see java.lang.String
     */
    public static EqualOperation parseField(String field) {
        EqualOperation typeEnum = RestField.parseField(EqualOperation.class, field);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }
}
