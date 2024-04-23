package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <p>ChiefModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceIdModel<M extends RiceIdModel<M, E>, E extends RiceIdEntity<E, M>> extends RestIdModel<M, E, String> {

    public RiceIdModel() {
    }

    public RiceIdModel(String id) {
        super(id);
    }

    public RiceIdModel(RiceIdModel.Builder<M, E> builder) {
        super(builder);
    }

    public static abstract class Builder<M extends RiceIdModel<M, E>, E extends RiceIdEntity<E, M>> extends RestIdModel.Builder<M, E, String> {

        public Builder() {
        }

        @Override
        public RiceIdModel.Builder<M, E> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RiceIdModel.Builder<M, E> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RiceIdModel.Builder<M, E> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public RiceIdModel.Builder<M, E> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RiceIdModel.Builder<M, E> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public RiceIdModel.Builder<M, E> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RiceIdModel.Builder<M, E> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public RiceIdModel.Builder<M, E> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public RiceIdModel.Builder<M, E> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public abstract RiceIdModel<M, E> build();
    }
}
