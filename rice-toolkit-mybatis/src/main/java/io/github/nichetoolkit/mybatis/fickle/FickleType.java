package io.github.nichetoolkit.mybatis.fickle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.nichetoolkit.rest.RestType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * <code>FickleType</code>
 * <p>The fickle type interface.</p>
 * @see  io.github.nichetoolkit.rest.RestType
 * @see  java.io.Serializable
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface FickleType extends RestType, Serializable {
    /**
     * <code>TYPE_HANDLER_REGISTRY</code>
     * {@link org.apache.ibatis.type.TypeHandlerRegistry} <p>The constant <code>TYPE_HANDLER_REGISTRY</code> field.</p>
     * @see  org.apache.ibatis.type.TypeHandlerRegistry
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    TypeHandlerRegistry TYPE_HANDLER_REGISTRY = new TypeHandlerRegistry();

    /**
     * <code>getAlias</code>
     * <p>The get alias getter method.</p>
     * @return  {@link java.lang.String} <p>The get alias return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.Nullable
     */
    @Nullable
    String getAlias();

    /**
     * <code>getJdbcType</code>
     * <p>The get jdbc type getter method.</p>
     * @return  {@link org.apache.ibatis.type.JdbcType} <p>The get jdbc type return object is <code>JdbcType</code> type.</p>
     * @see  org.apache.ibatis.type.JdbcType
     * @see  org.springframework.lang.Nullable
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @Nullable
    @JsonIgnore
    JdbcType getJdbcType();

    /**
     * <code>getJavaType</code>
     * <p>The get java type getter method.</p>
     * @return  {@link com.fasterxml.jackson.databind.JavaType} <p>The get java type return object is <code>JavaType</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  org.springframework.lang.Nullable
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @Nullable
    @JsonIgnore
    default JavaType getJavaType() {
       return TypeFactory.defaultInstance().constructType(getType());
    }

    /**
     * <code>getJdbcTypeHandler</code>
     * <p>The get jdbc type handler getter method.</p>
     * @return  {@link org.apache.ibatis.type.TypeHandler} <p>The get jdbc type handler return object is <code>TypeHandler</code> type.</p>
     * @see  org.apache.ibatis.type.TypeHandler
     * @see  org.springframework.lang.Nullable
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @Nullable
    @JsonIgnore
    default TypeHandler<?> getJdbcTypeHandler() {
        TypeHandler<?> typeHandler = null;
        if (getJdbcType() != null && getType() != null ) {
            typeHandler = TYPE_HANDLER_REGISTRY.getTypeHandler(getType(),getJdbcType());
        }
        if (typeHandler == null && getJdbcType() != null) {
            typeHandler = TYPE_HANDLER_REGISTRY.getTypeHandler(getJdbcType());
        }
        if (typeHandler == null && getType() != null) {
            typeHandler = TYPE_HANDLER_REGISTRY.getTypeHandler(getType());
        }
      return typeHandler;
    }

    /**
     * <code>getJacksonTypeHandler</code>
     * <p>The get jackson type handler getter method.</p>
     * @return  {@link java.lang.Object} <p>The get jackson type handler return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  org.springframework.lang.Nullable
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @Nullable
    @JsonIgnore
    default Object getJacksonTypeHandler() {
        if (getJavaType() != null) {
            return getJavaType().getTypeHandler();
        }
        return null;
    }

    @Nullable
    @Override
    @JsonIgnore
    Class<?> getType();

    @NonNull
    @JsonValue
    @Override
    Integer getKey();

    /**
     * <code>setKey</code>
     * <p>The set key setter method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @see  org.springframework.lang.NonNull
     * @see  com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    void setKey(@NonNull Integer key);

    @Nullable
    @Override
    default String getValue() {
        return getAlias();
    }
}
