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

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface FickleType extends RestType, Serializable {

    @Nullable
    String getAlias();

    @Nullable
    @JsonIgnore
    JdbcType getJdbcType();

    @Nullable
    @JsonIgnore
    default JavaType getJavaType() {
       return TypeFactory.defaultInstance().constructType(getType());
    }

    @Nullable
    @JsonIgnore
    default TypeHandler<?> getJdbcTypeHandler() {
      return null;
    }

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

    @Nullable
    @Override
    default String getValue() {
        return getAlias();
    }
}
