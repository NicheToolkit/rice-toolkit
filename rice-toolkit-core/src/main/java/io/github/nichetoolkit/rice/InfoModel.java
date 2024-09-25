package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>InfoModel</code>
 * <p>The type info model class.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @see io.github.nichetoolkit.rice.RestInfo
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoModel<I> extends IdModel<I> implements RestInfo<I> {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>the <code>name</code> field.</p>
     * @see java.lang.String
     */
    protected String name;
    /**
     * <code>description</code>
     * {@link java.lang.String} <p>the <code>description</code> field.</p>
     * @see java.lang.String
     */
    protected String description;

    /**
     * <code>InfoModel</code>
     * Instantiates a new info model.
     */
    public InfoModel() {
    }

    /**
     * <code>InfoModel</code>
     * Instantiates a new info model.
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     */
    public InfoModel(I id) {
        super(id);
    }

    /**
     * <code>InfoModel</code>
     * Instantiates a new info model.
     * @param name        {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param description {@link java.lang.String} <p>the description parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public InfoModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * <code>InfoModel</code>
     * Instantiates a new info model.
     * @param builder {@link io.github.nichetoolkit.rice.InfoModel.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.InfoModel.Builder
     */
    public InfoModel(InfoModel.Builder<I> builder) {
        super(builder);
        this.name = builder.name;
        this.description = builder.description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }

    public static <I> InfoModel.Builder<I> ofInfo() {
        return new InfoModel.Builder<>();
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.IdModel.Builder
     * @since Jdk1.8
     */
    public static class Builder<I> extends IdModel.Builder<I> {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>the <code>name</code> field.</p>
         * @see java.lang.String
         */
        protected String name;
        /**
         * <code>description</code>
         * {@link java.lang.String} <p>the <code>description</code> field.</p>
         * @see java.lang.String
         */
        protected String description;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>name</code>
         * <p>the method.</p>
         * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.InfoModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public InfoModel.Builder<I> name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>description</code>
         * <p>the method.</p>
         * @param description {@link java.lang.String} <p>the description parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.InfoModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public InfoModel.Builder<I> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public InfoModel.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public InfoModel.Builder<I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public InfoModel.Builder<I> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public InfoModel.Builder<I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public InfoModel.Builder<I> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public InfoModel.Builder<I> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public InfoModel.Builder<I> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public InfoModel.Builder<I> logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public InfoModel.Builder<I> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public InfoModel.Builder<I> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public InfoModel<I> build() {
            return new InfoModel<>(this);
        }
    }
}
