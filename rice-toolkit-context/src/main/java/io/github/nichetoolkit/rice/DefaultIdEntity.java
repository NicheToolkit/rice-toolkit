package io.github.nichetoolkit.rice;


import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>DefaultIdEntity</code>
 * <p>The type default id entity class.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultIdEntity} <p>the generic parameter is <code>DefaultIdEntity</code> type.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.DefaultIdModel} <p>the generic parameter is <code>DefaultIdModel</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultIdModel
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see io.github.nichetoolkit.rice.RestEntity
 * @since Jdk1.8
 */
public abstract class DefaultIdEntity<E extends DefaultIdEntity<E,M,I>, M extends DefaultIdModel<M,E,I>,I> extends IdEntity<I> implements RestEntity<I,M> {

    /**
     * <code>DefaultIdEntity</code>
     * Instantiates a new default id entity.
     */
    public DefaultIdEntity() {
    }

    /**
     * <code>DefaultIdEntity</code>
     * Instantiates a new default id entity.
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     */
    public DefaultIdEntity(I id) {
        super(id);
    }

    /**
     * <code>DefaultIdEntity</code>
     * Instantiates a new default id entity.
     * @param builder {@link io.github.nichetoolkit.rice.DefaultIdEntity.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.DefaultIdEntity.Builder
     */
    public DefaultIdEntity(Builder<E,M,I> builder) {
        super(builder);
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <E> {@link io.github.nichetoolkit.rice.DefaultIdEntity} <p>the generic parameter is <code>DefaultIdEntity</code> type.</p>
     * @param <M> {@link io.github.nichetoolkit.rice.DefaultIdModel} <p>the generic parameter is <code>DefaultIdModel</code> type.</p>
     * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.DefaultIdModel
     * @see io.github.nichetoolkit.rice.IdEntity.Builder
     * @since Jdk1.8
     */
    public static abstract class Builder<E extends DefaultIdEntity<E,M,I>,M extends DefaultIdModel<M,E,I>,I> extends IdEntity.Builder<I> {

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        public DefaultIdEntity.Builder<E,M,I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public DefaultIdEntity.Builder<E,M,I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public DefaultIdEntity.Builder<E,M,I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public DefaultIdEntity.Builder<E,M,I> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public DefaultIdEntity.Builder<E,M,I> operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public DefaultIdEntity.Builder<E,M,I> logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        @Override
        public abstract DefaultIdEntity<E,M,I> build();
    }
}
