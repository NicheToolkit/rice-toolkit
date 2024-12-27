package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>DefaultInfoEntity</code>
 * <p>The default info entity class.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.DefaultInfoEntity} <p>The generic parameter is <code>DefaultInfoEntity</code> type.</p>
 * @param <M>  {@link io.github.nichetoolkit.rice.DefaultInfoModel} <p>The generic parameter is <code>DefaultInfoModel</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.DefaultInfoModel
 * @see  io.github.nichetoolkit.rice.InfoEntity
 * @see  io.github.nichetoolkit.rice.RestEntity
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public abstract class DefaultInfoEntity<E extends DefaultInfoEntity<E,M,I>,M extends DefaultInfoModel<M,E,I>,I> extends InfoEntity<I> implements RestEntity<I,M> {

    /**
     * <code>DefaultInfoEntity</code>
     * <p>Instantiates a new default info entity.</p>
     */
    public DefaultInfoEntity() {
    }

    /**
     * <code>DefaultInfoEntity</code>
     * <p>Instantiates a new default info entity.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public DefaultInfoEntity(I id) {
        super(id);
    }

    /**
     * <code>DefaultInfoEntity</code>
     * <p>Instantiates a new default info entity.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public DefaultInfoEntity(I id, String name) {
        super(id, name);
    }

    /**
     * <code>DefaultInfoEntity</code>
     * <p>Instantiates a new default info entity.</p>
     * @param builder {@link io.github.nichetoolkit.rice.DefaultInfoEntity.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.DefaultInfoEntity.Builder
     */
    public DefaultInfoEntity(Builder<E,M,I> builder) {
        super(builder);
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @param <E>  {@link io.github.nichetoolkit.rice.DefaultInfoEntity} <p>The generic parameter is <code>DefaultInfoEntity</code> type.</p>
     * @param <M>  {@link io.github.nichetoolkit.rice.DefaultInfoModel} <p>The generic parameter is <code>DefaultInfoModel</code> type.</p>
     * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @see  io.github.nichetoolkit.rice.DefaultInfoModel
     * @see  io.github.nichetoolkit.rice.InfoEntity.Builder
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static abstract class Builder<E extends DefaultInfoEntity<E,M,I>,M extends DefaultInfoModel<M,E,I>,I> extends InfoEntity.Builder<I> {

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        @Override
        public DefaultInfoEntity.Builder<E,M,I> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public DefaultInfoEntity.Builder<E,M,I> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public DefaultInfoEntity.Builder<E,M,I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public DefaultInfoEntity.Builder<E,M,I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public DefaultInfoEntity.Builder<E,M,I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public DefaultInfoEntity.Builder<E,M,I> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public DefaultInfoEntity.Builder<E,M,I> operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public DefaultInfoEntity.Builder<E,M,I> logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public abstract DefaultInfoEntity<E,M,I> build();
    }
}
