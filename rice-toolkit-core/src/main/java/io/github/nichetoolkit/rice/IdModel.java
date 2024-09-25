package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Objects;

/**
 * <code>IdModel</code>
 * <p>The type id model class.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.TimeModel
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdModel<I> extends TimeModel implements RestId<I>{
    /**
     * <code>id</code>
     * <p>the <code>id</code> field.</p>
     */
    protected I id;

    /**
     * <code>IdModel</code>
     * Instantiates a new id model.
     */
    public IdModel() {
    }

    /**
     * <code>IdModel</code>
     * Instantiates a new id model.
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     */
    public IdModel(I id) {
        this.id = id;
    }

    /**
     * <code>IdModel</code>
     * Instantiates a new id model.
     * @param builder {@link io.github.nichetoolkit.rice.IdModel.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.IdModel.Builder
     */
    public IdModel(IdModel.Builder<I> builder) {
        super(builder);
        this.id = builder.id;
    }

    @Override
    public I getId() {
        return id;
    }

    @Override
    public void setId(I id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof IdModel)) return false;
        IdModel<?> idModel = (IdModel<?>) o;
        return Objects.equals(id, idModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }


    /**
     * <code>ofId</code>
     * <p>the id method.</p>
     * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rice.IdModel.Builder} <p>the id return object is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.IdModel.Builder
     */
    public static <I> IdModel.Builder<I> ofId() {
        return new IdModel.Builder<>();
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.TimeModel.Builder
     * @since Jdk1.8
     */
    public static class Builder<I> extends TimeModel.Builder {
        /**
         * <code>id</code>
         * <p>the <code>id</code> field.</p>
         */
        protected I id;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>id</code>
         * <p>the method.</p>
         * @param id I <p>the id parameter is <code>I</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.IdModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         */
        public IdModel.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public IdModel.Builder<I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public IdModel.Builder<I> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public IdModel.Builder<I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public IdModel.Builder<I> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public IdModel.Builder<I> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public IdModel.Builder<I> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public IdModel.Builder<I> logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public IdModel.Builder<I> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public IdModel.Builder<I> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public IdModel<I> build() {
            return new IdModel<>(this);
        }
    }
}
