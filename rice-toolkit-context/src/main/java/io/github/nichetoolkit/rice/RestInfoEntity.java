package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <p>RestInfoEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RestInfoEntity<E extends RestInfoEntity<E,M,I>,M extends RestInfoModel<M,E,I>,I> extends InfoEntity<I> implements RestEntity<I,M> {

    public RestInfoEntity() {
    }

    public RestInfoEntity(I id) {
        super(id);
    }

    public RestInfoEntity(I id, String name) {
        super(id, name);
    }

    public RestInfoEntity(Builder<E,M,I> builder) {
        super(builder);
    }

    public static abstract class Builder<E extends RestInfoEntity<E,M,I>,M extends RestInfoModel<M,E,I>,I> extends InfoEntity.Builder<I> {

        public Builder() {
        }

        @Override
        public RestInfoEntity.Builder<E,M,I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E,M,I> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E,M,I> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E,M,I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E,M,I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E,M,I> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E,M,I> operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E,M,I> logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        @Override
        public abstract RestInfoEntity<E,M,I> build();
    }
}
