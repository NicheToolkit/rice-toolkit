package io.github.nichetoolkit.mybatis.fickle.pack;

import io.github.nichetoolkit.mybatis.enums.MybatisType;
import io.github.nichetoolkit.mybatis.fickle.RestFickleType;
import io.github.nichetoolkit.rest.RestType;
import org.apache.ibatis.type.JdbcType;

public  class OfRestFickleType extends RestType.OfRestType implements RestFickleType {

    public OfRestFickleType(Integer key, String value, Class<?> type) {
        super(key, value, type);
    }

    public OfRestFickleType(RestType type) {
        super(type.getKey(), type.getValue(), type.getType());
    }

    public OfRestFickleType(RestType type, String value) {
        super(type.getKey(), value, type.getType());
    }

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