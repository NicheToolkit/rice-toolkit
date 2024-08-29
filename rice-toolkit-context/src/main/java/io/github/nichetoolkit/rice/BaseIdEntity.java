package io.github.nichetoolkit.rice;


import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Date;

public abstract class BaseIdEntity<E extends BaseIdEntity<E,M,I>, M extends BaseIdModel<M,E,I>,I> extends IdEntity<I> implements RestEntity<I,M> {

    public BaseIdEntity() {
    }

    public BaseIdEntity(I id) {
        super(id);
    }

    public BaseIdEntity(Builder<E,M,I> builder) {
        super(builder);
    }

    public static abstract class Builder<E extends BaseIdEntity<E,M,I>,M extends BaseIdModel<M,E,I>,I> extends IdEntity.Builder<I> {

        public Builder() {
        }

        public BaseIdEntity.Builder<E,M,I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public BaseIdEntity.Builder<E,M,I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public BaseIdEntity.Builder<E,M,I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public BaseIdEntity.Builder<E,M,I> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public BaseIdEntity.Builder<E,M,I> operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public BaseIdEntity.Builder<E,M,I> logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        @Override
        public abstract BaseIdEntity<E,M,I> build();
    }
}
