package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.stereotype.mybatis.column.RestForce;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import java.util.Date;

/**
 * <p>TimeEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeEntity extends OperateEntity {
    /** 数据创建时间 */
    @Column(name = "create_time")
    protected Date createTime;
    /** 数据更新时间 */
    @RestForce("now()")
    @Column(name = "update_time")
    protected Date updateTime;

    public TimeEntity() {
    }

    public TimeEntity(TimeEntity.Builder builder) {
        super(builder);
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @SuppressWarnings("WeakerAccess")
    public static class Builder extends OperateEntity.Builder {
        protected Date createTime;
        protected Date updateTime;

        public Builder() {
        }

        public TimeEntity.Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public TimeEntity.Builder updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public TimeEntity.Builder operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public TimeEntity.Builder operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public TimeEntity.Builder logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        @Override
        public TimeEntity build() {
            return new TimeEntity(this);
        }
    }
}
