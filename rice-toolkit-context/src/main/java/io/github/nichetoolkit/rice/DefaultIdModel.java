package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>DefaultIdModel</code>
 * <p>The type default id model class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.DefaultIdModel} <p>the generic parameter is <code>DefaultIdModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultIdEntity} <p>the generic parameter is <code>DefaultIdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultIdEntity
 * @see io.github.nichetoolkit.rice.IdModel
 * @see io.github.nichetoolkit.rice.RestModel
 * @since Jdk1.8
 */
public abstract class DefaultIdModel<M extends DefaultIdModel<M,E,I>,E extends DefaultIdEntity<E,M,I>,I> extends IdModel<I> implements RestModel<I,E> {

    /**
     * <code>DefaultIdModel</code>
     * Instantiates a new default id model.
     */
    public DefaultIdModel() {
    }

    /**
     * <code>DefaultIdModel</code>
     * Instantiates a new default id model.
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     */
    public DefaultIdModel(I id) {
        super(id);
    }

    /**
     * <code>DefaultIdModel</code>
     * Instantiates a new default id model.
     * @param builder {@link io.github.nichetoolkit.rice.DefaultIdModel.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.DefaultIdModel.Builder
     */
    public DefaultIdModel(DefaultIdModel.Builder<M,E,I> builder) {
        super(builder);
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <M> {@link io.github.nichetoolkit.rice.DefaultIdModel} <p>the generic parameter is <code>DefaultIdModel</code> type.</p>
     * @param <E> {@link io.github.nichetoolkit.rice.DefaultIdEntity} <p>the generic parameter is <code>DefaultIdEntity</code> type.</p>
     * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.DefaultIdEntity
     * @see io.github.nichetoolkit.rice.IdModel.Builder
     * @since Jdk1.8
     */
    public static abstract class Builder<M extends DefaultIdModel<M,E,I>,E extends DefaultIdEntity<E,M,I>,I> extends IdModel.Builder<I> {

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        public DefaultIdModel.Builder<M,E,I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public DefaultIdModel.Builder<M,E,I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public DefaultIdModel.Builder<M,E,I> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public DefaultIdModel.Builder<M,E,I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public DefaultIdModel.Builder<M,E,I> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public DefaultIdModel.Builder<M,E,I> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public DefaultIdModel.Builder<M,E,I> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public DefaultIdModel.Builder<M,E,I> logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public DefaultIdModel.Builder<M,E,I> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public DefaultIdModel.Builder<M,E,I> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }
        
        @Override
        public abstract DefaultIdModel<M,E,I> build();
    }
}
