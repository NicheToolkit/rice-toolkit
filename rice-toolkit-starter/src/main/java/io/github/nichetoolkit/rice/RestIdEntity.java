package io.github.nichetoolkit.rice;


import io.github.nichetoolkit.rice.enums.OperateType;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * <p>RestIdEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RestIdEntity<E extends RestIdEntity<E,M,I>, M extends RestIdModel<M,E,I>,I> extends IdEntity<I> implements RestEntity<I,M> {

    public RestIdEntity() {
    }

    public RestIdEntity(I id) {
        super(id);
    }

    public RestIdEntity(Builder<E,M,I> builder) {
        super(builder);
    }

    public static abstract class Builder<E extends RestIdEntity<E,M,I>,M extends RestIdModel<M,E,I>,I> extends IdEntity.Builder<I> {

        public Builder() {
        }

        public RestIdEntity.Builder<E,M,I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public RestIdEntity.Builder<E,M,I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RestIdEntity.Builder<E,M,I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RestIdEntity.Builder<E,M,I> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestIdEntity.Builder<E,M,I> operate(@NotNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public RestIdEntity.Builder<E,M,I> logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        @Override
        public abstract RestIdEntity<E,M,I> build();
    }
}
