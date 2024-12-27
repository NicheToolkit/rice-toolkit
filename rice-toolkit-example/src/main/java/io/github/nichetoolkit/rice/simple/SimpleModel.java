package io.github.nichetoolkit.rice.simple;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.RestInfoModel;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>SimpleModel</code>
 * <p>The simple model class.</p>
 * @see  io.github.nichetoolkit.rice.RestInfoModel
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleModel extends RestInfoModel<SimpleModel, SimpleEntity> {
    /**
     * <code>time</code>
     * {@link java.util.Date} <p>The <code>time</code> field.</p>
     * @see  java.util.Date
     * @see  org.springframework.format.annotation.DateTimeFormat
     * @see  com.fasterxml.jackson.annotation.JsonFormat
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    /**
     * <code>SimpleModel</code>
     * <p>Instantiates a new simple model.</p>
     */
    public SimpleModel() {
    }

    /**
     * <code>SimpleModel</code>
     * <p>Instantiates a new simple model.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public SimpleModel(String id) {
        super(id);
    }

    /**
     * <code>SimpleModel</code>
     * <p>Instantiates a new simple model.</p>
     * @param builder {@link io.github.nichetoolkit.rice.simple.SimpleModel.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.simple.SimpleModel.Builder
     */
    public SimpleModel(Builder builder) {
        super(builder);
        this.time = builder.time;
    }

    /**
     * <code>getTime</code>
     * <p>The get time getter method.</p>
     * @return  {@link java.util.Date} <p>The get time return object is <code>Date</code> type.</p>
     * @see  java.util.Date
     */
    public Date getTime() {
        return time;
    }

    /**
     * <code>setTime</code>
     * <p>The set time setter method.</p>
     * @param time {@link java.util.Date} <p>The time parameter is <code>Date</code> type.</p>
     * @see  java.util.Date
     */
    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public SimpleEntity toEntity() {
        SimpleEntity entity = new SimpleEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());
        return entity;
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @see  io.github.nichetoolkit.rice.RestInfoModel.Builder
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder extends RestInfoModel.Builder<SimpleModel, SimpleEntity> {
        /**
         * <code>time</code>
         * {@link java.util.Date} <p>The <code>time</code> field.</p>
         * @see  java.util.Date
         */
        protected Date time;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>time</code>
         * <p>The time method.</p>
         * @param time {@link java.lang.Long} <p>The time parameter is <code>Long</code> type.</p>
         * @see  java.lang.Long
         * @return  {@link io.github.nichetoolkit.rice.simple.SimpleModel.Builder} <p>The time return object is <code>Builder</code> type.</p>
         */
        public SimpleModel.Builder time(Long time) {
            this.time = new Date(time);
            return this;
        }

        /**
         * <code>time</code>
         * <p>The time method.</p>
         * @param time {@link java.util.Date} <p>The time parameter is <code>Date</code> type.</p>
         * @see  java.util.Date
         * @return  {@link io.github.nichetoolkit.rice.simple.SimpleModel.Builder} <p>The time return object is <code>Builder</code> type.</p>
         */
        public SimpleModel.Builder time(Date time) {
            this.time = time;
            return this;
        }

        @Override
        public SimpleModel.Builder id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public SimpleModel.Builder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public SimpleModel.Builder description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public SimpleModel.Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public SimpleModel.Builder createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public SimpleModel.Builder updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public SimpleModel.Builder updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public SimpleModel.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public SimpleModel.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public SimpleModel.Builder save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public SimpleModel.Builder save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public SimpleModel build() {
            return new SimpleModel(this);
        }
    }
}
