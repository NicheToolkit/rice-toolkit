package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <p>RiceInfoEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceInfoEntity<E extends RiceInfoEntity<E, M>, M extends RiceInfoModel<M, E>> extends RestInfoEntity<E, M, String> {

    public RiceInfoEntity() {
    }

    public RiceInfoEntity(String id) {
        super(id);
    }

    public RiceInfoEntity(String id, String name) {
        super(id, name);
    }

    public RiceInfoEntity(Builder<E, M> builder) {
        super(builder);
    }

    public static abstract class Builder<E extends RiceInfoEntity<E, M>, M extends RiceInfoModel<M, E>> extends RestInfoEntity.Builder<E, M, String> {

        public Builder() {
        }

        @Override
        public RiceInfoEntity.Builder<E, M> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RiceInfoEntity.Builder<E, M> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RiceInfoEntity.Builder<E, M> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public RiceInfoEntity.Builder<E, M> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RiceInfoEntity.Builder<E, M> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RiceInfoEntity.Builder<E, M> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RiceInfoEntity.Builder<E, M> operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public RiceInfoEntity.Builder<E, M> logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        @Override
        public abstract RiceInfoEntity<E, M> build();
    }
}
