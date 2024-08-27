package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <p>RestIdModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RestIdModel<M extends RestIdModel<M,E,I>,E extends RestIdEntity<E,M,I>,I> extends IdModel<I> implements RestModel<I,E> {

    public RestIdModel() {
    }

    public RestIdModel(I id) {
        super(id);
    }

    public RestIdModel(RestIdModel.Builder<M,E,I> builder) {
        super(builder);
    }

    public static abstract class Builder<M extends RestIdModel<M,E,I>,E extends RestIdEntity<E,M,I>,I> extends IdModel.Builder<I> {

        public Builder() {
        }

        public RestIdModel.Builder<M,E,I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public RestIdModel.Builder<M,E,I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RestIdModel.Builder<M,E,I> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public RestIdModel.Builder<M,E,I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RestIdModel.Builder<M,E,I> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public RestIdModel.Builder<M,E,I> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestIdModel.Builder<M,E,I> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public RestIdModel.Builder<M,E,I> save(SaveType save) {
            this.save = save;
            return this;
        }
        @Override
        public RestIdModel.Builder<M,E,I> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }
        
        @Override
        public abstract RestIdModel<M,E,I> build();
    }
}
