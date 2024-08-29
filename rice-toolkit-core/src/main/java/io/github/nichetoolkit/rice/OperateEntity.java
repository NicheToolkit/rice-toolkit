package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.mybatis.stereotype.column.RestLogic;
import io.github.nichetoolkit.mybatis.stereotype.column.RestOperate;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateEntity implements Serializable {

    @RestOperate
    protected Integer operate;

    @RestLogic
    protected String logicSign;

    public OperateEntity() {
    }

    public OperateEntity(OperateEntity.Builder builder) {
        this.operate = builder.operate;
        this.logicSign = builder.logicSign;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    public String getLogicSign() {
        return logicSign;
    }

    public void setLogicSign(String logicSign) {
        this.logicSign = logicSign;
    }

    @SuppressWarnings("WeakerAccess")
    public static class Builder {
        protected Integer operate;
        protected String logicSign;

        public Builder() {
        }

        public OperateEntity.Builder operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        public OperateEntity.Builder operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        public OperateEntity.Builder logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        public OperateEntity build() {
            return new OperateEntity(this);
        }
    }
}
