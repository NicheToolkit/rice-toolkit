package io.github.nichetoolkit.rice;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>TimeEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TimeEntity implements Serializable {
    /** 数据创建时间 */
    @Column(name = "create_time")
    protected Date createTime;
    /** 数据更新时间 */
    @Column(name = "update_time")
    protected Date updateTime;

    public TimeEntity() {
    }

    public TimeEntity(TimeEntity.Builder builder) {
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

    public static class Builder {
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

        public TimeEntity build() {
            return new TimeEntity(this);
        }
    }
}
