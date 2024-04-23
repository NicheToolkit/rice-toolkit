package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <p>RiceInfoModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceInfoModel<M extends RiceInfoModel<M, E>, E extends RiceInfoEntity<E, M>> extends RestInfoModel<M, E, String> {

    public RiceInfoModel() {
    }

    public RiceInfoModel(String id) {
        super(id);
    }

    public RiceInfoModel(String name, String description) {
        super(name, description);
    }

    public RiceInfoModel(RiceInfoModel.Builder<M, E> builder) {
        super(builder);
    }

    public static abstract class Builder<M extends RiceInfoModel<M, E>, E extends RiceInfoEntity<E, M>> extends RestInfoModel.Builder<M, E, String> {

        public Builder() {
        }

        @Override
        public RiceInfoModel.Builder<M, E> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RiceInfoModel.Builder<M, E> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RiceInfoModel.Builder<M, E> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public RiceInfoModel.Builder<M, E> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RiceInfoModel.Builder<M, E> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public RiceInfoModel.Builder<M, E> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RiceInfoModel.Builder<M, E> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public RiceInfoModel.Builder<M, E> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RiceInfoModel.Builder<M, E> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public RiceInfoModel.Builder<M, E> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public RiceInfoModel.Builder<M, E> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public abstract RiceInfoModel<M, E> build();
    }
}
