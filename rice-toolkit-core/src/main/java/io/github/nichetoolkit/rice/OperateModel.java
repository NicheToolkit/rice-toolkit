package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;

import java.io.Serializable;

/**
 * <p>OperateModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class OperateModel implements RestOperate, Serializable {

    protected OperateType operate;

    public OperateModel() {
    }

    public OperateModel(OperateModel.Builder builder) {
        this.operate = builder.operate;
    }

    @Override
    public OperateType getOperate() {
        return operate;
    }

    @Override
    public void setOperate(OperateType operate) {
        this.operate = operate;
    }

    public static class Builder {
        protected OperateType operate = OperateType.NONE;

        public Builder() {
        }

        public OperateModel.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        public OperateModel.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        public OperateModel build() {
            return new OperateModel(this);
        }
    }
}
