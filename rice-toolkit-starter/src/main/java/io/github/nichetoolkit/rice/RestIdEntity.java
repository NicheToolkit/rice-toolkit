package io.github.nichetoolkit.rice;


import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>RestIdEntity</code>
 * <p>The type rest id entity class.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>the generic parameter is <code>RestIdEntity</code> type.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestIdModel} <p>the generic parameter is <code>RestIdModel</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestIdModel
 * @see io.github.nichetoolkit.rice.DefaultIdEntity
 * @since Jdk1.8
 */
public abstract class RestIdEntity<E extends RestIdEntity<E, M>, M extends RestIdModel<M, E>> extends DefaultIdEntity<E, M, String> {

    /**
     * <code>RestIdEntity</code>
     * Instantiates a new rest id entity.
     */
    public RestIdEntity() {
    }

    /**
     * <code>RestIdEntity</code>
     * Instantiates a new rest id entity.
     * @param id {@link java.lang.String} <p>the id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestIdEntity(String id) {
        super(id);
    }

    /**
     * <code>RestIdEntity</code>
     * Instantiates a new rest id entity.
     * @param builder {@link io.github.nichetoolkit.rice.RestIdEntity.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestIdEntity.Builder
     */
    public RestIdEntity(Builder<E, M> builder) {
        super(builder);
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>the generic parameter is <code>RestIdEntity</code> type.</p>
     * @param <M> {@link io.github.nichetoolkit.rice.RestIdModel} <p>the generic parameter is <code>RestIdModel</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.RestIdModel
     * @see io.github.nichetoolkit.rice.DefaultIdEntity.Builder
     * @since Jdk1.8
     */
    public static abstract class Builder<E extends RestIdEntity<E, M>, M extends RestIdModel<M, E>> extends DefaultIdEntity.Builder<E, M, String> {

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        @Override
        public RestIdEntity.Builder<E, M> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RestIdEntity.Builder<E, M> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RestIdEntity.Builder<E, M> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RestIdEntity.Builder<E, M> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestIdEntity.Builder<E, M> operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public RestIdEntity.Builder<E, M> logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public abstract RestIdEntity<E, M> build();
    }
}
