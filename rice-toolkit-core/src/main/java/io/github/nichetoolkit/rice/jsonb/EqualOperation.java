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
     * <p>The equal operation equal operation field.</p>
     */
    EQUAL_OPERATION(1, "相等","target = 'values'"),
    /**
     * <code>LEFT_LIKE_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>LEFT_LIKE_OPERATION</code> field.</p>
     */
    LEFT_LIKE_OPERATION(2, "左模糊","target like concat('%','values')"),
    /**
     * <code>RIGHT_LIKE_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>RIGHT_LIKE_OPERATION</code> field.</p>
     */
    RIGHT_LIKE_OPERATION(3, "右模糊","target like concat('values','%')"),
    /**
     * <code>ALL_LIKE_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>ALL_LIKE_OPERATION</code> field.</p>
     */
    ALL_LIKE_OPERATION(4, "全模糊","target like concat('%','values','%')"),
    /**
     * <code>NOT_NULL_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>NOT_NULL_OPERATION</code> field.</p>
     */
    NOT_NULL_OPERATION(5, "不为空","target is not null"),
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
     * <code>field</code>
     * {@link java.lang.String} <p>The <code>field</code> field.</p>
     * @see java.lang.String
     */
    private final String field;

    /**
     * <code>TARGET</code>
     * {@link java.lang.String} <p>The constant <code>TARGET</code> field.</p>
     * @see java.lang.String
     */
    public static final String TARGET = "target";
    /**
     * <code>VALUE</code>
     * {@link java.lang.String} <p>The constant <code>VALUE</code> field.</p>
     * @see java.lang.String
     */
    public static final String VALUE = "values";

    /**
     * <code>EqualOperation</code>
     * <p>Instantiates a new equal operation.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
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
     * <p>The sql method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.String} <p>The sql return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public String translateSql(String target, Object value) {
        return this.field.replace(TARGET, target).replace(VALUE, String.valueOf(value));
    }

    /**
     * <code>parseKey</code>
     * <p>The key method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The key return object is <code>EqualOperation</code> type.</p>
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
     * <p>The value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The value return object is <code>EqualOperation</code> type.</p>
     * @see java.lang.String
     */
    public static EqualOperation parseValue(String value) {
        EqualOperation typeEnum = RestValue.parseValue(EqualOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }

    /**
     * <code>parseField</code>
     * <p>The field method.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The field return object is <code>EqualOperation</code> type.</p>
     * @see java.lang.String
     */
    public static EqualOperation parseField(String field) {
        EqualOperation typeEnum = RestField.parseField(EqualOperation.class, field);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }
}
