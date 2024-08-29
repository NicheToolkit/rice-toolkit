package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;

public abstract class BaseInfoModel<M extends BaseInfoModel<M, E, I>, E extends BaseInfoEntity<E, M, I>, I> extends InfoModel<I> implements RestModel<I, E> {

    public BaseInfoModel() {
    }

    public BaseInfoModel(I id) {
        super(id);
    }

    public BaseInfoModel(String name, String description) {
        super(name, description);
    }

    public BaseInfoModel(BaseInfoModel.Builder<M, E, I> builder) {
        super(builder);
    }

    public static abstract class Builder<M extends BaseInfoModel<M, E, I>, E extends BaseInfoEntity<E, M, I>, I> extends InfoModel.Builder<I> {

        public Builder() {
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public BaseInfoModel.Builder<M, E, I> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public abstract BaseInfoModel<M, E, I> build();
    }
}
