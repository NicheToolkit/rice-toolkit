package io.github.nichetoolkit.mybatis.fickle;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.nichetoolkit.mybatis.enums.MybatisType;
import io.github.nichetoolkit.mybatis.fickle.pack.OfRestFickleType;
import io.github.nichetoolkit.rest.RestType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.Serializable;

public interface RestFickleType extends RestType, Serializable {

    String getAlias();

    JdbcType getJdbcType();

    default JavaType getJavaType() {
        return TypeFactory.defaultInstance().constructType(getType());
    }

    @JsonIgnore
    default TypeHandler<?> getJdbcTypeHandler() {
        return MybatisType.parseClazz(this.getType()).getJdbcTypeHandler();
    }

    @JsonIgnore
    default Object getJacksonTypeHandler() {
        if (getJavaType() != null) {
            return getJavaType().getTypeHandler();
        }
        return null;
    }

    @Override
    default String getValue() {
        return getAlias();
    }

    static RestFickleType of(Integer key) {
        MybatisType mybatisType = MybatisType.parseKey(key);
        return new OfRestFickleType(key, mybatisType.getValue(), mybatisType.getType());
    }

    static RestFickleType of(RestType type) {
        RestFickleType fickleType;
        if (type instanceof RestFickleType) {
            fickleType = (RestFickleType) type;
        } else {
            fickleType = RestFickleType.ofType(type);
        }
        return fickleType;
    }

    static RestFickleType of(Integer key, String value) {
        MybatisType mybatisType = MybatisType.parseKey(key);
        return new OfRestFickleType(key, value, mybatisType.getType());
    }

    static RestFickleType of(Integer key, Class<?> type) {
        MybatisType mybatisType = MybatisType.parseKey(key);
        return new OfRestFickleType(key, mybatisType.getValue(), type);
    }

    static RestFickleType of(Integer key, String value, Class<?> type) {
        return new OfRestFickleType(key, value, type);
    }

    static RestFickleType ofValue(RestType type, String value) {
        return new OfRestFickleType(type.getKey(), value, type.getType());
    }

    static RestFickleType ofValue(String value) {
        MybatisType mybatisType = MybatisType.parseAlias(value);
        return new OfRestFickleType(mybatisType.getKey(), value, mybatisType.getType());
    }

    static RestFickleType ofType(RestType type) {
        return new OfRestFickleType(type.getKey(), type.getValue(), type.getType());
    }

    static RestFickleType ofType(RestType type, Class<?> clazz) {
        return new OfRestFickleType(type.getKey(), type.getValue(), clazz);
    }

    static RestFickleType ofType(Class<?> clazz) {
        MybatisType mybatisType = MybatisType.parseClazz(clazz);
        return new OfRestFickleType(mybatisType.getKey(), mybatisType.getValue(), clazz);
    }

    static RestFickleType ofNull() {
        return new OfRestFickleType(MybatisType.OBJECT);
    }


}
