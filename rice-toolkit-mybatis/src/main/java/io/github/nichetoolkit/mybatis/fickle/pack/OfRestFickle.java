package io.github.nichetoolkit.mybatis.fickle.pack;

import io.github.nichetoolkit.mybatis.enums.MybatisType;
import io.github.nichetoolkit.mybatis.fickle.RestFickle;
import io.github.nichetoolkit.mybatis.fickle.RestFickleType;
import io.github.nichetoolkit.rest.RestEntry;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Setter;

import java.util.Objects;

@Setter
public class OfRestFickle<F> extends RestValue.OfRestValue<String, F> implements RestFickle<F> {
    private String name;

    private RestFickleType type = MybatisType.OBJECT;

    public OfRestFickle(String name) {
        super(null, null);
        this.name = name;
    }

    public OfRestFickle(String name, RestEntry<String, F> entry) {
        super(entry);
        this.name = name;
        if (GeneralUtils.isNotEmpty(entry.getValue())) {
            this.type = RestFickleType.ofType(entry.getValue().getClass());
        }
    }

    public OfRestFickle(String name, String key, F value, boolean fickleType) {
        super(null, value);
        this.name = name;
        if (fickleType && GeneralUtils.isNotEmpty(value)) {
            this.type = RestFickleType.ofType(value.getClass());
        }
    }

    public OfRestFickle(String name, F value, RestFickleType type) {
        super(null, value);
        this.name = name;
        if (GeneralUtils.isNotEmpty(value)) {
            this.type = RestFickleType.ofType(type, value.getClass());
        } else {
            this.type = type;
        }
    }

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