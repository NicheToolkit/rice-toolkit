package io.github.nichetoolkit.mybatis.fickle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JavaType;
import io.github.nichetoolkit.mybatis.enums.MybatisType;
import io.github.nichetoolkit.rest.RestEntry;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestType;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * <code>RestFickle</code>
 * <p>The rest fickle interface.</p>
 * @param <F> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestField
 * @see java.io.Serializable
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface RestFickle<F> extends RestField<F>, Serializable {

    @Override
    @JsonValue
    String getKey();

    @Override
    RestFickleType getType();

    /**
     * <code>getDescription</code>
     * <p>The get description getter method.</p>
     * @return {@link java.lang.String} <p>The get description return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String getDescription() {
        return getComment();
    }

    /**
     * <code>isValid</code>
     * <p>The is valid method.</p>
     * @return boolean <p>The is valid return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default boolean isValid() {
        return GeneralUtils.isValid(getKey());
    }

    /**
     * <code>isEmpty</code>
     * <p>The is empty method.</p>
     * @return boolean <p>The is empty return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default boolean isEmpty() {
        return GeneralUtils.isEmpty(getValue());
    }

    /**
     * <code>getJdbcType</code>
     * <p>The get jdbc type getter method.</p>
     * @return {@link org.apache.ibatis.type.JdbcType} <p>The get jdbc type return object is <code>JdbcType</code> type.</p>
     * @see org.apache.ibatis.type.JdbcType
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default JdbcType getJdbcType() {
        return getType().getJdbcType();
    }

    /**
     * <code>getJavaType</code>
     * <p>The get java type getter method.</p>
     * @return {@link com.fasterxml.jackson.databind.JavaType} <p>The get java type return object is <code>JavaType</code> type.</p>
     * @see com.fasterxml.jackson.databind.JavaType
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default JavaType getJavaType() {
        return getType().getJavaType();
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
        return getType().getJdbcTypeHandler();
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
        return getType().getJacksonTypeHandler();
    }


    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <F>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     */
    static <F> RestFickle<F> of(String name) {
        return new OfRestFickle<>(name);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <F>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     */
    static <F> RestFickle<F> of(String name, F value) {
        return new OfRestFickle<>(name, null, value, false);
    }

    /**
     * <code>ofKey</code>
     * <p>The of key method.</p>
     * @param <F> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of key return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     */
    static <F> RestFickle<F> ofKey(String key) {
        return new OfRestFickle<>(null, key, null, false);
    }

    /**
     * <code>ofKey</code>
     * <p>The of key method.</p>
     * @param <F>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of key return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     */
    static <F> RestFickle<F> ofKey(String key, F value) {
        return new OfRestFickle<>(null, key, value, false);
    }

    /**
     * <code>ofValue</code>
     * <p>The of value method.</p>
     * @param <F>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of value return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     */
    static <F> RestFickle<F> ofValue(String name, F value) {
        return new OfRestFickle<>(name, null, value, true);
    }

    /**
     * <code>ofValue</code>
     * <p>The of value method.</p>
     * @param <F>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of value return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     */
    static <F> RestFickle<F> ofValue(String name, String key, F value) {
        return new OfRestFickle<>(name, key, value, true);
    }

    /**
     * <code>ofEntry</code>
     * <p>The of entry method.</p>
     * @param <F>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestEntry} <p>The entry parameter is <code>RestEntry</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of entry return object is <code>RestFickle</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestEntry
     */
    static <F> RestFickle<F> ofEntry(RestEntry<String, F> entry) {
        return new OfRestFickle<>(null, entry);
    }

    /**
     * <code>ofEntry</code>
     * <p>The of entry method.</p>
     * @param <F>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestEntry} <p>The entry parameter is <code>RestEntry</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of entry return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestEntry
     */
    static <F> RestFickle<F> ofEntry(String name, RestEntry<String, F> entry) {
        return new OfRestFickle<>(name, entry);
    }

    /**
     * <code>ofType</code>
     * <p>The of type method.</p>
     * @param <F>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of type return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestType
     */
    static <F> RestFickle<F> ofType(String name, RestType type) {
        return new OfRestFickle<>(name, null, null, RestFickleType.of(type));
    }

    /**
     * <code>ofType</code>
     * <p>The of type method.</p>
     * @param <F>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name      {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type      {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
     * @param typeValue {@link java.lang.String} <p>The type value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of type return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestType
     */
    static <F> RestFickle<F> ofType(String name, RestType type, String typeValue) {
        return new OfRestFickle<>(name, null, null, RestFickleType.ofValue(type, typeValue));
    }

    /**
     * <code>ofType</code>
     * <p>The of type method.</p>
     * @param <F>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type  {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of type return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestType
     * @see java.lang.Class
     */
    static <F> RestFickle<F> ofType(String name, RestType type, Class<?> clazz) {
        return new OfRestFickle<>(name, null, null, RestFickleType.ofType(type, clazz));
    }

    /**
     * <code>ofType</code>
     * <p>The of type method.</p>
     * @param <F>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @param type  {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of type return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestType
     */
    static <F> RestFickle<F> ofType(String name, F value, RestType type) {
        return new OfRestFickle<>(name, null, value, RestFickleType.of(type));
    }

    /**
     * <code>ofType</code>
     * <p>The of type method.</p>
     * @param <F>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @param type  {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of type return object is <code>RestFickle</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestType
     */
    static <F> RestFickle<F> ofType(String name, String key, F value, RestType type) {
        return new OfRestFickle<>(name, key, value, RestFickleType.of(type));
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <F> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The of null return object is <code>RestFickle</code> type.</p>
     */
    static <F> RestFickle<F> ofNull() {
        return new OfRestFickle<>(null);
    }

    /**
     * <code>OfRestFickle</code>
     * <p>The of rest fickle class.</p>
     * @param <F> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.RestValue.OfRestValue
     * @see lombok.Setter
     * @since Jdk1.8
     */
    @Setter
    class OfRestFickle<F> extends RestValue.OfRestValue<String, F> implements RestFickle<F> {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>The <code>name</code> field.</p>
         * @see java.lang.String
         */
        private String name;

        /**
         * <code>type</code>
         * {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The <code>type</code> field.</p>
         * @see io.github.nichetoolkit.mybatis.fickle.RestFickleType
         */
        private RestFickleType type = MybatisType.OBJECT;

        /**
         * <code>OfRestFickle</code>
         * <p>Instantiates a new of rest fickle.</p>
         * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public OfRestFickle(String name) {
            super(null, null);
            this.name = name;
        }

        /**
         * <code>OfRestFickle</code>
         * <p>Instantiates a new of rest fickle.</p>
         * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @param entry {@link io.github.nichetoolkit.rest.RestEntry} <p>The entry parameter is <code>RestEntry</code> type.</p>
         * @see java.lang.String
         * @see io.github.nichetoolkit.rest.RestEntry
         */
        public OfRestFickle(String name, RestEntry<String, F> entry) {
            super(entry);
            this.name = name;
            if (GeneralUtils.isNotEmpty(entry.getValue())) {
                this.type = RestFickleType.ofType(entry.getValue().getClass());
            }
        }

        /**
         * <code>OfRestFickle</code>
         * <p>Instantiates a new of rest fickle.</p>
         * @param name       {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @param key        {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
         * @param value      F <p>The value parameter is <code>F</code> type.</p>
         * @param fickleType boolean <p>The fickle type parameter is <code>boolean</code> type.</p>
         * @see java.lang.String
         */
        public OfRestFickle(String name, String key, F value, boolean fickleType) {
            super(null, value);
            this.name = name;
            if (fickleType && GeneralUtils.isNotEmpty(value)) {
                this.type = RestFickleType.ofType(value.getClass());
            }
        }

        /**
         * <code>OfRestFickle</code>
         * <p>Instantiates a new of rest fickle.</p>
         * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @param value F <p>The value parameter is <code>F</code> type.</p>
         * @param type  {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The type parameter is <code>RestFickleType</code> type.</p>
         * @see java.lang.String
         * @see io.github.nichetoolkit.mybatis.fickle.RestFickleType
         */
        public OfRestFickle(String name, F value, RestFickleType type) {
            super(null, value);
            this.name = name;
            if (GeneralUtils.isNotEmpty(value)) {
                this.type = RestFickleType.ofType(type, value.getClass());
            } else {
                this.type = type;
            }
        }

        /**
         * <code>OfRestFickle</code>
         * <p>Instantiates a new of rest fickle.</p>
         * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
         * @param value F <p>The value parameter is <code>F</code> type.</p>
         * @param type  {@link io.github.nichetoolkit.mybatis.fickle.RestFickleType} <p>The type parameter is <code>RestFickleType</code> type.</p>
         * @see java.lang.String
         * @see io.github.nichetoolkit.mybatis.fickle.RestFickleType
         */
        public OfRestFickle(String name, String key, F value, RestFickleType type) {
            super(key, value);
            this.name = name;
            if (GeneralUtils.isNotEmpty(value)) {
                this.type = RestFickleType.ofType(type, value.getClass());
            } else {
                this.type = type;
            }
        }

        @Override
        public String getAlias() {
            return null;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @NonNull
        @Override
        public RestFickleType getType() {
            return this.type;
        }

        @Override
        public String getComment() {
            return "";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            OfRestFickle<?> that = (OfRestFickle<?>) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), name);
        }
    }


}
