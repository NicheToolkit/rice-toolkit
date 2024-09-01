package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Date;

public abstract class RestInfoEntity<E extends RestInfoEntity<E, M>, M extends RestInfoModel<M, E>> extends DefaultInfoEntity<E, M, String> {

    public RestInfoEntity() {
    }

    public RestInfoEntity(String id) {
        super(id);
    }

    public RestInfoEntity(String id, String name) {
        super(id, name);
    }

    public RestInfoEntity(Builder<E, M> builder) {
        super(builder);
    }

    public static abstract class Builder<E extends RestInfoEntity<E, M>, M extends RestInfoModel<M, E>> extends DefaultInfoEntity.Builder<E, M, String> {

        public Builder() {
        }

        @Override
        public RestInfoEntity.Builder<E, M> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        @Override
        public abstract RestInfoEntity<E, M> build();
    }
}
