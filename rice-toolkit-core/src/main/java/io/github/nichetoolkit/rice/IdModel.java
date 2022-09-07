package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Objects;

/**
 * <p>IdModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class IdModel<I> extends TimeModel implements RestId<I>{
    protected I id;

    public IdModel() {
    }

    public IdModel(I id) {
        this.id = id;
    }

    public IdModel(IdModel.Builder<I> builder) {
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
        if (o == null) return false;
        if (!(o instanceof IdModel)) return false;
        IdModel<?> idModel = (IdModel<?>) o;
        return Objects.equals(id, idModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }

    public static class Builder<I> extends TimeModel.Builder {
        protected I id;

        public Builder() {
        }

        public IdModel.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public IdModel.Builder<I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public IdModel.Builder<I> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public IdModel.Builder<I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public IdModel.Builder<I> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }



        @Override
        public IdModel.Builder<I> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public IdModel.Builder<I> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public IdModel.Builder<I> save(SaveType save) {
            this.save = save;
            return this;
        }
        @Override
        public IdModel.Builder<I> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public IdModel<I> build() {
            return new IdModel<>(this);
        }
    }
}
