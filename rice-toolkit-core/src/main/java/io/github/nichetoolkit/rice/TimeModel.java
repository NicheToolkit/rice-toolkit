package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <p>TimeModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TimeModel extends OperateModel {
    /** 数据创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;
    /** 数据更新时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updateTime;

    public TimeModel() {
    }

    public TimeModel(TimeModel.Builder builder) {
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

    public static class Builder extends OperateModel.Builder{
        protected Date createTime;
        protected Date updateTime;

        public Builder() {
        }

        public TimeModel.Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public TimeModel.Builder createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        public TimeModel.Builder updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public TimeModel.Builder updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public TimeModel.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public TimeModel.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public TimeModel build() {
            return new TimeModel(this);
        }
    }
}
