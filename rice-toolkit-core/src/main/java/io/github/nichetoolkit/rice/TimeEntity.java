package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.mybatis.stereotype.column.RestForceInsert;
import io.github.nichetoolkit.mybatis.stereotype.column.RestForceUpdate;
import io.github.nichetoolkit.mybatis.stereotype.column.RestUpdate;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>TimeEntity</code>
 * <p>The type time entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.OperateEntity
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeEntity extends OperateEntity {
    /**
     * <code>createTime</code>
     * {@link java.util.Date} <p>the <code>createTime</code> field.</p>
     * @see java.util.Date
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestUpdate
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestForceInsert
     */
    @RestUpdate(false)
    @RestForceInsert("now()")
    protected Date createTime;
    /**
     * <code>updateTime</code>
     * {@link java.util.Date} <p>the <code>updateTime</code> field.</p>
     * @see java.util.Date
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestForceInsert
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestForceUpdate
     */
    @RestForceInsert("now()")
    @RestForceUpdate("now()")
    protected Date updateTime;

    /**
     * <code>TimeEntity</code>
     * Instantiates a new time entity.
     */
    public TimeEntity() {
    }

    /**
     * <code>TimeEntity</code>
     * Instantiates a new time entity.
     * @param builder {@link io.github.nichetoolkit.rice.TimeEntity.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.TimeEntity.Builder
     */
    public TimeEntity(TimeEntity.Builder builder) {
        super(builder);
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
    }

    /**
     * <code>getCreateTime</code>
     * <p>the create time getter method.</p>
     * @return {@link java.util.Date} <p>the create time return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <code>setCreateTime</code>
     * <p>the create time setter method.</p>
     * @param createTime {@link java.util.Date} <p>the create time parameter is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * <code>getUpdateTime</code>
     * <p>the update time getter method.</p>
     * @return {@link java.util.Date} <p>the update time return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * <code>setUpdateTime</code>
     * <p>the update time setter method.</p>
     * @param updateTime {@link java.util.Date} <p>the update time parameter is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.OperateEntity.Builder
     * @see java.lang.SuppressWarnings
     * @since Jdk1.8
     */
    @SuppressWarnings("WeakerAccess")
    public static class Builder extends OperateEntity.Builder {
        /**
         * <code>createTime</code>
         * {@link java.util.Date} <p>the <code>createTime</code> field.</p>
         * @see java.util.Date
         */
        protected Date createTime;
        /**
         * <code>updateTime</code>
         * {@link java.util.Date} <p>the <code>updateTime</code> field.</p>
         * @see java.util.Date
         */
        protected Date updateTime;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>createTime</code>
         * <p>the time method.</p>
         * @param createTime {@link java.util.Date} <p>the create time parameter is <code>Date</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.TimeEntity.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.util.Date
         */
        public TimeEntity.Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        /**
         * <code>updateTime</code>
         * <p>the time method.</p>
         * @param updateTime {@link java.util.Date} <p>the update time parameter is <code>Date</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.TimeEntity.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.util.Date
         */
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
