package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestStamp;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>EqualOperation</code>
 * <p>The equal operation enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestStamp
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)  
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
public enum EqualOperation implements RestStamp {
    /**
     * <code>EQUAL_OPERATION</code>
     * <p>The equal operation equal operation stamp.</p>
     */
    EQUAL_OPERATION(1, "=","target = 'values'"),
    /**
     * <code>LEFT_LIKE_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>LEFT_LIKE_OPERATION</code> stamp.</p>
     */
    LEFT_LIKE_OPERATION(2, "LIKE '%value'","target like concat('%','values')"),
    /**
     * <code>RIGHT_LIKE_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>RIGHT_LIKE_OPERATION</code> stamp.</p>
     */
    RIGHT_LIKE_OPERATION(3, "LIKE 'value%'","target like concat('values','%')"),
    /**
     * <code>ALL_LIKE_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>ALL_LIKE_OPERATION</code> stamp.</p>
     */
    ALL_LIKE_OPERATION(4, "LIKE '%value%'","target like concat('%','values','%')"),
    /**
     * <code>NOT_NULL_OPERATION</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>NOT_NULL_OPERATION</code> stamp.</p>
     */
    NOT_NULL_OPERATION(5, "IS NOT NULL","target is not null"),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>The <code>key</code> stamp.</p>
     * @see  java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> stamp.</p>
     * @see  java.lang.String
     */
    private final String value;
    /**
     * <code>stamp</code>
     * {@link java.lang.String} <p>The <code>stamp</code> stamp.</p>
     * @see  java.lang.String
     */
    private final String stamp;

    /**
     * <code>TARGET</code>
     * {@link java.lang.String} <p>The constant <code>TARGET</code> stamp.</p>
     * @see  java.lang.String
     */
    public static final String TARGET = "target";
    /**
     * <code>VALUE</code>
     * {@link java.lang.String} <p>The constant <code>VALUE</code> stamp.</p>
     * @see  java.lang.String
     */
    public static final String VALUE = "values";

    /**
     * <code>EqualOperation</code>
     * <p>Instantiates a new equal operation.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>  
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>  
     * @param stamp {@link java.lang.String} <p>The stamp parameter is <code>String</code> type.</p> 
     * @see  java.lang.Integer
     * @see  java.lang.String
     */
    EqualOperation(Integer key, String value, String stamp) {
        this.key = key;
        this.value = value;
        this.stamp = stamp;
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
    public String getStamp() {
        return this.stamp;
    }

    /**
     * <code>translateSql</code>
     * <p>The translate sql method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>  
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>  
     * @see  java.lang.String
     * @see  java.lang.Object
     * @return  {@link java.lang.String} <p>The translate sql return object is <code>String</code> type.</p> 
     */
    public String translateSql(String target, Object value) {
        return this.stamp.replace(TARGET, target).replace(VALUE, String.valueOf(value));
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>  
     * @see  java.lang.Integer
     * @see  com.fasterxml.jackson.annotation.JsonCreator
     * @return  {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The parse key return object is <code>EqualOperation</code> type.</p>  
     */
    @JsonCreator
    public static EqualOperation parseKey(Integer key) {
        EqualOperation typeEnum = RestValue.parseKey(EqualOperation.class, key);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>  
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The parse value return object is <code>EqualOperation</code> type.</p> 
     */
    public static EqualOperation parseValue(String value) {
        EqualOperation typeEnum = RestValue.parseValue(EqualOperation.class, value);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }

    /**
     * <code>parseStamp</code>
     * <p>The parse stamp method.</p>
     * @param stamp {@link java.lang.String} <p>The stamp parameter is <code>String</code> type.</p>  
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The parse stamp return object is <code>EqualOperation</code> type.</p> 
     */
    public static EqualOperation parseStamp(String stamp) {
        EqualOperation typeEnum = RestStamp.parseStamp(EqualOperation.class, stamp);
        return Optional.ofNullable(typeEnum).orElse(EqualOperation.EQUAL_OPERATION);
    }
}
