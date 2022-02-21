package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * <p>OperateEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class OperateEntity implements Serializable {

    /** 数据操作 */
    protected Integer operate;

    public OperateEntity() {
    }

    public OperateEntity(OperateEntity.Builder builder) {
        this.operate = builder.operate;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    @SuppressWarnings("WeakerAccess")
    public static class Builder {
        protected Integer operate;

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

        public OperateEntity build() {
            return new OperateEntity(this);
        }
    }
}
