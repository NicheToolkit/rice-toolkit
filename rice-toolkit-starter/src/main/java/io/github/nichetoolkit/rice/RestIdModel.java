package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>RestIdModel</code>
 * <p>The type rest id model class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestIdModel} <p>the generic parameter is <code>RestIdModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>the generic parameter is <code>RestIdEntity</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestIdEntity
 * @see io.github.nichetoolkit.rice.DefaultIdModel
 * @since Jdk1.8
 */
public abstract class RestIdModel<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> extends DefaultIdModel<M, E, String> {

    /**
     * <code>RestIdModel</code>
     * Instantiates a new rest id model.
     */
    public RestIdModel() {
    }

    /**
     * <code>RestIdModel</code>
     * Instantiates a new rest id model.
     * @param id {@link java.lang.String} <p>the id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestIdModel(String id) {
        super(id);
    }

    /**
     * <code>RestIdModel</code>
     * Instantiates a new rest id model.
     * @param builder {@link io.github.nichetoolkit.rice.RestIdModel.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestIdModel.Builder
     */
    public RestIdModel(RestIdModel.Builder<M, E> builder) {
        super(builder);
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <M> {@link io.github.nichetoolkit.rice.RestIdModel} <p>the generic parameter is <code>RestIdModel</code> type.</p>
     * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>the generic parameter is <code>RestIdEntity</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.RestIdEntity
     * @see io.github.nichetoolkit.rice.DefaultIdModel.Builder
     * @since Jdk1.8
     */
    public static abstract class Builder<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> extends DefaultIdModel.Builder<M, E, String> {

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        @Override
        public RestIdModel.Builder<M, E> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public RestIdModel.Builder<M, E> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public abstract RestIdModel<M, E> build();
    }
}
