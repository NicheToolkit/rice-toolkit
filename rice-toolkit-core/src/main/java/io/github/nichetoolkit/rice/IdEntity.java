package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.common.JsonUtils;

import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * <p>IdEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class IdEntity<I> extends TimeEntity implements RestId<I> {
    @Id
    protected I id;

    public IdEntity() {
    }

    public IdEntity(I id) {
        this.id = id;
    }

    public IdEntity(IdEntity.Builder<I> builder) {
        super(builder);
        this.id = builder.id;
    }

    @Override
    public I getId() {
        return id;
    }

    @Override
    public void setId(I id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdEntity)) return false;
        IdEntity<?> idEntity = (IdEntity<?>) o;
        return Objects.equals(id, idEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }

    public static class Builder<I> extends TimeEntity.Builder {
        protected I id;

        public Builder() {
        }

        public IdEntity.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public IdEntity.Builder<I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public IdEntity.Builder<I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public IdEntity<I> build() {
            return new IdEntity<>(this);
        }
    }
}
