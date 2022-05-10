package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <p>ChiefModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceInfoModel<M extends RiceInfoModel<M,E>,E extends RiceInfoEntity<E,M>> extends InfoModel<String> implements RestModel<String,E> {

    public RiceInfoModel() {
    }

    public RiceInfoModel(String id) {
        super(id);
    }

    public RiceInfoModel(String name, String description) {
        super(name, description);
    }

    public RiceInfoModel(RiceInfoModel.Builder builder) {
        super(builder);
    }

    public static abstract class Builder extends InfoModel.Builder<String> {

        public Builder() {
        }

        @Override
        public RiceInfoModel.Builder id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RiceInfoModel.Builder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RiceInfoModel.Builder description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public RiceInfoModel.Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RiceInfoModel.Builder createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public RiceInfoModel.Builder updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RiceInfoModel.Builder updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public RiceInfoModel.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RiceInfoModel.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public abstract RiceInfoModel<?,?> build();
    }
}
