package io.github.nichetoolkit.mybatis.fickle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JavaType;
import io.github.nichetoolkit.mybatis.fickle.pack.OfRestFickle;
import io.github.nichetoolkit.rest.RestEntry;
import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rest.RestType;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.Serializable;

public interface RestFickle<F> extends RestField<F>, Serializable {

    @Override
    RestFickleType getType();

    default String getDescription() {
        return getComment();
    }

    @JsonIgnore
    default boolean isValid() {
        return GeneralUtils.isValid(getKey());
    }

    @JsonIgnore
    default boolean isEmpty() {
        return GeneralUtils.isEmpty(getValue());
    }

    default JdbcType getJdbcType() {
        return getType().getJdbcType();
    }

    default JavaType getJavaType() {
        return getType().getJavaType();
    }

    @JsonIgnore
    default TypeHandler<?> getJdbcTypeHandler() {
        return getType().getJdbcTypeHandler();
    }

    @JsonIgnore
    default Object getJacksonTypeHandler() {
        return getType().getJacksonTypeHandler();
    }


    static <F> RestFickle<F> of(String name) {
        return new OfRestFickle<>(name);
    }

    static <F> RestFickle<F> of(String name, F value) {
        return new OfRestFickle<>(name, null, value, false);
    }

    static <F> RestFickle<F> ofKey(String key) {
        return new OfRestFickle<>(null, key, null, false);
    }

    static <F> RestFickle<F> ofKey(String key, F value) {
        return new OfRestFickle<>(null, key, value, false);
    }

    static <F> RestFickle<F> ofValue(String name, F value) {
        return new OfRestFickle<>(name, null, value, true);
    }

    static <F> RestFickle<F> ofValue(String name, String key, F value) {
        return new OfRestFickle<>(name, key, value, true);
    }

    static <F> RestFickle<F> ofEntry(RestEntry<String, F> entry) {
        return new OfRestFickle<>(null, entry);
    }

    static <F> RestFickle<F> ofEntry(String name, RestEntry<String, F> entry) {
        return new OfRestFickle<>(name, entry);
    }

    static <F> RestFickle<F> ofType(String name, RestType type) {
        return new OfRestFickle<>(name, null, null, RestFickleType.of(type));
    }

    static <F> RestFickle<F> ofType(String name, RestType type, String typeValue) {
        return new OfRestFickle<>(name, null, null, RestFickleType.ofValue(type, typeValue));
    }

    static <F> RestFickle<F> ofType(String name, RestType type, Class<?> clazz) {
        return new OfRestFickle<>(name, null, null, RestFickleType.ofType(type, clazz));
    }

    static <F> RestFickle<F> ofType(String name, F value, RestType type) {
        return new OfRestFickle<>(name, null, value, RestFickleType.of(type));
    }

    static <F> RestFickle<F> ofType(String name, String key, F value, RestType type) {
        return new OfRestFickle<>(name, key, value, RestFickleType.of(type));
    }

    static <F> RestFickle<F> ofNull() {
        return new OfRestFickle<>(null);
    }




}
