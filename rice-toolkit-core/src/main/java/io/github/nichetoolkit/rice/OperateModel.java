package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;

@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateModel extends LogicModel implements RestOperate<OperateType>{

    protected OperateType operate;

    public OperateModel() {
    }

    public OperateModel(OperateModel.Builder builder) {
        super(builder);
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

    @JsonIgnore
    public boolean isOperateNone() {
        return this.operate == OperateType.NONE;
    }

    @JsonIgnore
    public boolean isOperateCreate() {
        return this.operate == OperateType.INSERT;
    }

    @JsonIgnore
    public boolean isOperateUpdate() {
        return this.operate == OperateType.UPDATE;
    }

    @JsonIgnore
    public boolean isOperateCopy() {
        return this.operate == OperateType.COPY;
    }

    @JsonIgnore
    public boolean isOperateRemove() {
        return this.operate == OperateType.REMOVE;
    }

    @JsonIgnore
    public boolean isOperateDelete() {
        return this.operate == OperateType.DELETE;
    }

    public static OperateModel.Builder ofOperate() {
        return new OperateModel.Builder();
    }

    public static class Builder extends LogicModel.Builder {
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

        @Override
        public OperateModel.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public OperateModel.Builder save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public OperateModel.Builder save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public OperateModel build() {
            return new OperateModel(this);
        }
    }
}
