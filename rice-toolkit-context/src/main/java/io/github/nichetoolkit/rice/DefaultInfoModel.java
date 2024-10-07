package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>DefaultInfoModel</code>
 * <p>The type default info model class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.DefaultInfoModel} <p>The generic parameter is <code>DefaultInfoModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultInfoEntity} <p>The generic parameter is <code>DefaultInfoEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultInfoEntity
 * @see io.github.nichetoolkit.rice.InfoModel
 * @see io.github.nichetoolkit.rice.RestModel
 * @since Jdk1.8
 */
public abstract class DefaultInfoModel<M extends DefaultInfoModel<M, E, I>, E extends DefaultInfoEntity<E, M, I>, I> extends InfoModel<I> implements RestModel<I, E> {

    /**
     * <code>DefaultInfoModel</code>
     * <p>Instantiates a new default info model.</p>
     */
    public DefaultInfoModel() {
    }

    /**
     * <code>DefaultInfoModel</code>
     * <p>Instantiates a new default info model.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public DefaultInfoModel(I id) {
        super(id);
    }

    /**
     * <code>DefaultInfoModel</code>
     * <p>Instantiates a new default info model.</p>
     * @param name        {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param description {@link java.lang.String} <p>The description parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DefaultInfoModel(String name, String description) {
        super(name, description);
    }

    /**
     * <code>DefaultInfoModel</code>
     * <p>Instantiates a new default info model.</p>
     * @param builder {@link io.github.nichetoolkit.rice.DefaultInfoModel.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.DefaultInfoModel.Builder
     */
    public DefaultInfoModel(DefaultInfoModel.Builder<M, E, I> builder) {
        super(builder);
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <M> {@link io.github.nichetoolkit.rice.DefaultInfoModel} <p>The generic parameter is <code>DefaultInfoModel</code> type.</p>
     * @param <E> {@link io.github.nichetoolkit.rice.DefaultInfoEntity} <p>The generic parameter is <code>DefaultInfoEntity</code> type.</p>
     * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.DefaultInfoEntity
     * @see io.github.nichetoolkit.rice.InfoModel.Builder
     * @since Jdk1.8
     */
    public static abstract class Builder<M extends DefaultInfoModel<M, E, I>, E extends DefaultInfoEntity<E, M, I>, I> extends InfoModel.Builder<I> {

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public DefaultInfoModel.Builder<M, E, I> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public abstract DefaultInfoModel<M, E, I> build();
    }
}
