package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;

public abstract class RestIdModel<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> extends DefaultIdModel<M, E, String> {

    public RestIdModel() {
    }

    public RestIdModel(String id) {
        super(id);
    }

    public RestIdModel(RestIdModel.Builder<M, E> builder) {
        super(builder);
    }

    public static abstract class Builder<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> extends DefaultIdModel.Builder<M, E, String> {

        public Builder() {
        }

        @Override
        public RestIdModel.Builder<M, E> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public abstract RestIdModel<M, E> build();
    }
}
