package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>TimeModel</code>
 * <p>The type time model class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.OperateModel
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeModel extends OperateModel {
    /**
     * <code>createTime</code>
     * {@link java.util.Date} <p>the <code>createTime</code> field.</p>
     * @see java.util.Date
     * @see org.springframework.format.annotation.DateTimeFormat
     * @see com.fasterxml.jackson.annotation.JsonFormat
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;
    /**
     * <code>updateTime</code>
     * {@link java.util.Date} <p>the <code>updateTime</code> field.</p>
     * @see java.util.Date
     * @see org.springframework.format.annotation.DateTimeFormat
     * @see com.fasterxml.jackson.annotation.JsonFormat
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updateTime;

    /**
     * <code>TimeModel</code>
     * Instantiates a new time model.
     */
    public TimeModel() {
    }

    /**
     * <code>TimeModel</code>
     * Instantiates a new time model.
     * @param builder {@link io.github.nichetoolkit.rice.TimeModel.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.TimeModel.Builder
     */
    public TimeModel(TimeModel.Builder builder) {
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

    public static TimeModel.Builder ofTime() {
        return new TimeModel.Builder();
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.OperateModel.Builder
     * @since Jdk1.8
     */
    public static class Builder extends OperateModel.Builder{
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
         * @return {@link io.github.nichetoolkit.rice.TimeModel.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.util.Date
         */
        public TimeModel.Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        /**
         * <code>createTime</code>
         * <p>the time method.</p>
         * @param createTime {@link java.lang.Long} <p>the create time parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.TimeModel.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         * @see org.springframework.lang.NonNull
         */
        public TimeModel.Builder createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        /**
         * <code>updateTime</code>
         * <p>the time method.</p>
         * @param updateTime {@link java.util.Date} <p>the update time parameter is <code>Date</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.TimeModel.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.util.Date
         */
        public TimeModel.Builder updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        /**
         * <code>updateTime</code>
         * <p>the time method.</p>
         * @param updateTime {@link java.lang.Long} <p>the update time parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.TimeModel.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         * @see org.springframework.lang.NonNull
         */
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
        public TimeModel.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public TimeModel.Builder save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public TimeModel.Builder save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public TimeModel build() {
            return new TimeModel(this);
        }
    }
}
