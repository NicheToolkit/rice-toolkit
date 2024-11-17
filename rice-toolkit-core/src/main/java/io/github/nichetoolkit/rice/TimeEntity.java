package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.consts.ScriptConstants;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.mybatis.stereotype.column.RestForceInsert;
import io.github.nichetoolkit.mybatis.stereotype.column.RestForceUpdate;
import io.github.nichetoolkit.mybatis.stereotype.column.RestUpdate;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>TimeEntity</code>
 * <p>The time entity class.</p>
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
     * {@link java.util.Date} <p>The <code>createTime</code> field.</p>
     * @see java.util.Date
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestUpdate
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestForceInsert
     */
    @RestUpdate(false)
    @RestForceInsert(ScriptConstants.NOW)
    protected Date createTime;
    /**
     * <code>updateTime</code>
     * {@link java.util.Date} <p>The <code>updateTime</code> field.</p>
     * @see java.util.Date
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestForceInsert
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestForceUpdate
     */
    @RestForceInsert(ScriptConstants.NOW)
    @RestForceUpdate(ScriptConstants.NOW)
    protected Date updateTime;

    /**
     * <code>TimeEntity</code>
     * <p>Instantiates a new time entity.</p>
     */
    public TimeEntity() {
    }

    /**
     * <code>TimeEntity</code>
     * <p>Instantiates a new time entity.</p>
     * @param builder {@link io.github.nichetoolkit.rice.TimeEntity.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.TimeEntity.Builder
     */
    public TimeEntity(TimeEntity.Builder builder) {
        super(builder);
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
    }

    /**
     * <code>getCreateTime</code>
     * <p>The get create time getter method.</p>
     * @return {@link java.util.Date} <p>The get create time return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <code>setCreateTime</code>
     * <p>The set create time setter method.</p>
     * @param createTime {@link java.util.Date} <p>The create time parameter is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * <code>getUpdateTime</code>
     * <p>The get update time getter method.</p>
     * @return {@link java.util.Date} <p>The get update time return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * <code>setUpdateTime</code>
     * <p>The set update time setter method.</p>
     * @param updateTime {@link java.util.Date} <p>The update time parameter is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.OperateEntity.Builder
     * @see java.lang.SuppressWarnings
     * @since Jdk1.8
     */
    @SuppressWarnings("WeakerAccess")
    public static class Builder extends OperateEntity.Builder {
        /**
         * <code>createTime</code>
         * {@link java.util.Date} <p>The <code>createTime</code> field.</p>
         * @see java.util.Date
         */
        protected Date createTime;
        /**
         * <code>updateTime</code>
         * {@link java.util.Date} <p>The <code>updateTime</code> field.</p>
         * @see java.util.Date
         */
        protected Date updateTime;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>createTime</code>
         * <p>The create time method.</p>
         * @param createTime {@link java.util.Date} <p>The create time parameter is <code>Date</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.TimeEntity.Builder} <p>The create time return object is <code>Builder</code> type.</p>
         * @see java.util.Date
         */
        public TimeEntity.Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        /**
         * <code>updateTime</code>
         * <p>The update time method.</p>
         * @param updateTime {@link java.util.Date} <p>The update time parameter is <code>Date</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.TimeEntity.Builder} <p>The update time return object is <code>Builder</code> type.</p>
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
        public TimeEntity.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public TimeEntity build() {
            return new TimeEntity(this);
        }
    }
}
