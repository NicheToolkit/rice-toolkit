package io.github.nichetoolkit.mybatis.fickle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.nichetoolkit.mybatis.enums.MybatisType;
import io.github.nichetoolkit.rest.RestType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.Serializable;

/**
 * <code>RestFickleType</code>
 * <p>The rest fickle type interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestType
 * @see java.io.Serializable
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface RestFickleType extends RestType, Serializable {

    /**
     * <code>getAlias</code>
     * <p>The get alias getter method.</p>
     * @return {@link java.lang.String} <p>The get alias return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getAlias();

    /**
     * <code>getJdbcType</code>
     * <p>The get jdbc type getter method.</p>
     * @return {@link org.apache.ibatis.type.JdbcType} <p>The get jdbc type return object is <code>JdbcType</code> type.</p>
     * @see org.apache.ibatis.type.JdbcType
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    JdbcType getJdbcType();

    /**
     * <code>getJavaType</code>
     * <p>The get java type getter method.</p>
     * @return {@link com.fasterxml.jackson.databind.JavaType} <p>The get java type return object is <code>JavaType</code> type.</p>
     * @see com.fasterxml.jackson.databind.JavaType
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default JavaType getJavaType() {
        return TypeFactory.defaultInstance().constructType(getType());
    }

    /**
     * <code>getJdbcTypeHandler</code>
     * <p>The get jdbc type handler getter method.</p>
     * @return {@link org.apache.ibatis.type.TypeHandler} <p>The get jdbc type handler return object is <code>TypeHandler</code> type.</p>
     * @see org.apache.ibatis.type.TypeHandler
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default TypeHandler<?> getJdbcTypeHandler() {
        return MybatisType.parseClazz(this.getType()).getJdbcTypeHandler();
    }

    /**
     * <code>getJacksonTypeHandler</code>
     * <p>The get jackson type handler getter method.</p>
     * @return {@link java.lang.Object} <p>The get jackson type handler return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default Object getJacksonTypeHandler() {
        if (getJavaType() != null) {
            return getJavaType().getTypeHandler();
        }
        return null;
    }

    @Override
    @JsonIgnore
    Class<?> getType();

    @JsonValue
    @Override
    Integer getKey();

    @Override
    default String getValue() {
        return getAlias();
    }


    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of return object is <code>RestFickleType</code> type.</p>
     * @see java.lang.Integer
     */
    static RestFickleType of(Integer key) {
        MybatisType mybatisType = MybatisType.parseKey(key);
        return new RestFickleType.OfRestFickleType(key, mybatisType.getValue(), mybatisType.getType());
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param type {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of return object is <code>RestFickleType</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestType
     */
    static RestFickleType of(RestType type) {
        RestFickleType fickleType;
        if (type instanceof RestFickleType) {
            fickleType = (RestFickleType) type;
        } else {
            fickleType = RestFickleType.ofType(type);
        }
        return fickleType;
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of return object is <code>RestFickleType</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    static RestFickleType of(Integer key, String value) {
        MybatisType mybatisType = MybatisType.parseKey(key);
        return new RestFickleType.OfRestFickleType(key, value, mybatisType.getType());
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key  {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param type {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of return object is <code>RestFickleType</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Class
     */
    static RestFickleType of(Integer key, Class<?> type) {
        MybatisType mybatisType = MybatisType.parseKey(key);
        return new RestFickleType.OfRestFickleType(key, mybatisType.getValue(), type);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param type  {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of return object is <code>RestFickleType</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Class
     */
    static RestFickleType of(Integer key, String value, Class<?> type) {
        return new RestFickleType.OfRestFickleType(key, value, type);
    }

    /**
     * <code>ofValue</code>
     * <p>The of value method.</p>
     * @param type  {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of value return object is <code>RestFickleType</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestType
     * @see java.lang.String
     */
    static RestFickleType ofValue(RestType type, String value) {
        return new RestFickleType.OfRestFickleType(type.getKey(), value, type.getType());
    }

    /**
     * <code>ofValue</code>
     * <p>The of value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of value return object is <code>RestFickleType</code> type.</p>
     * @see java.lang.String
     */
    static RestFickleType ofValue(String value) {
        MybatisType mybatisType = MybatisType.parseAlias(value);
        return new RestFickleType.OfRestFickleType(mybatisType.getKey(), value, mybatisType.getType());
    }

    /**
     * <code>ofType</code>
     * <p>The of type method.</p>
     * @param type {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of type return object is <code>RestFickleType</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestType
     */
    static RestFickleType ofType(RestType type) {
        return new RestFickleType.OfRestFickleType(type.getKey(), type.getValue(), type.getType());
    }

    /**
     * <code>ofType</code>
     * <p>The of type method.</p>
     * @param type  {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of type return object is <code>RestFickleType</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestType
     * @see java.lang.Class
     */
    static RestFickleType ofType(RestType type, Class<?> clazz) {
        return new RestFickleType.OfRestFickleType(type.getKey(), type.getValue(), clazz);
    }

    /**
     * <code>ofType</code>
     * <p>The of type method.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of type return object is <code>RestFickleType</code> type.</p>
     * @see java.lang.Class
     */
    static RestFickleType ofType(Class<?> clazz) {
        MybatisType mybatisType = MybatisType.parseClazz(clazz);
        return new RestFickleType.OfRestFickleType(mybatisType.getKey(), mybatisType.getValue(), clazz);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The of null return object is <code>RestFickleType</code> type.</p>
     */
    static RestFickleType ofNull() {
        return new RestFickleType.OfRestFickleType(MybatisType.OBJECT);
    }

    /**
     * <code>OfRestFickleType</code>
     * <p>The of rest fickle type class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.RestType.OfRestType
     * @since Jdk1.8
     */
    class OfRestFickleType extends RestType.OfRestType implements RestFickleType {

        /**
         * <code>OfRestFickleType</code>
         * <p>Instantiates a new of rest fickle type.</p>
         * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
         * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
         * @param type  {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
         * @see java.lang.Integer
         * @see java.lang.String
         * @see java.lang.Class
         */
        public OfRestFickleType(Integer key, String value, Class<?> type) {
            super(key, value, type);
        }

        /**
         * <code>OfRestFickleType</code>
         * <p>Instantiates a new of rest fickle type.</p>
         * @param type {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestType
         */
        public OfRestFickleType(RestType type) {
            super(type.getKey(), type.getValue(), type.getType());
        }

        /**
         * <code>OfRestFickleType</code>
         * <p>Instantiates a new of rest fickle type.</p>
         * @param type  {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
         * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestType
         * @see java.lang.String
         */
        public OfRestFickleType(RestType type, String value) {
            super(type.getKey(), value, type.getType());
        }

        /**
         * <code>OfRestFickleType</code>
         * <p>Instantiates a new of rest fickle type.</p>
         * @param type  {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
         * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestType
         * @see java.lang.Class
         */
        public OfRestFickleType(RestType type, Class<?> clazz) {
            super(type.getKey(), type.getValue(), clazz);
        }

        @Override
        public String getAlias() {
            return null;
        }

        @Override
        public JdbcType getJdbcType() {
            return MybatisType.parseClazz(this.getType()).getJdbcType();
        }

    }
}
