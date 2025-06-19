package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import lombok.experimental.SuperBuilder;

@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofOperateBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateModel extends LogicModel implements RestOperate{

    protected OperateType operate;

    public OperateModel() {
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

}
