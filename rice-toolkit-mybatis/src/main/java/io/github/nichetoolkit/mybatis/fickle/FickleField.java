package io.github.nichetoolkit.mybatis.fickle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JavaType;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * <code>FickleField</code>
 * <p>The fickle field interface.</p>
 * @param <F>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rest.RestField
 * @see  java.io.Serializable
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface FickleField<F> extends RestField<F>, Serializable {

    @NonNull
    @Override
    @JsonValue
    String getKey();

    /**
     * <code>setKey</code>
     * <p>The set key setter method.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    void setKey(@NonNull String key);

    @NonNull
    @Override
    FickleType getType();

    /**
     * <code>getDescription</code>
     * <p>The get description getter method.</p>
     * @return  {@link java.lang.String} <p>The get description return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    default String getDescription() {
        return getComment();
    }

    /**
     * <code>isValid</code>
     * <p>The is valid method.</p>
     * @return boolean <p>The is valid return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default boolean isValid() {
        return GeneralUtils.isValid(getKey());
    }

    /**
     * <code>isEmpty</code>
     * <p>The is empty method.</p>
     * @return boolean <p>The is empty return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default boolean isEmpty() {
        return GeneralUtils.isEmpty(getValue());
    }

    /**
     * <code>getJdbcType</code>
     * <p>The get jdbc type getter method.</p>
     * @return  {@link org.apache.ibatis.type.JdbcType} <p>The get jdbc type return object is <code>JdbcType</code> type.</p>
     * @see  org.apache.ibatis.type.JdbcType
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default JdbcType getJdbcType() {
        return getType().getJdbcType();
    }

    /**
     * <code>getJavaType</code>
     * <p>The get java type getter method.</p>
     * @return  {@link com.fasterxml.jackson.databind.JavaType} <p>The get java type return object is <code>JavaType</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default JavaType getJavaType() {
        return getType().getJavaType();
    }

    /**
     * <code>getJdbcTypeHandler</code>
     * <p>The get jdbc type handler getter method.</p>
     * @return  {@link org.apache.ibatis.type.TypeHandler} <p>The get jdbc type handler return object is <code>TypeHandler</code> type.</p>
     * @see  org.apache.ibatis.type.TypeHandler
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default TypeHandler<?> getJdbcTypeHandler() {
        return getType().getJdbcTypeHandler();
    }

    /**
     * <code>getJacksonTypeHandler</code>
     * <p>The get jackson type handler getter method.</p>
     * @return  {@link java.lang.Object} <p>The get jackson type handler return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default Object getJacksonTypeHandler() {
        return  getType().getJacksonTypeHandler();
    }


}
