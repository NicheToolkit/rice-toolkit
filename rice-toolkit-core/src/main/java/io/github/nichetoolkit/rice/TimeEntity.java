package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.mybatis.stereotype.column.RestForceInsert;
import io.github.nichetoolkit.mybatis.stereotype.column.RestForceUpdate;
import io.github.nichetoolkit.mybatis.stereotype.column.RestUpdate;
import org.springframework.lang.NonNull;

import java.util.Date;

@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeEntity extends OperateEntity {
    @RestUpdate(false)
    @RestForceInsert("now()")
    protected Date createTime;
    @RestForceInsert("now()")
    @RestForceUpdate("now()")
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
