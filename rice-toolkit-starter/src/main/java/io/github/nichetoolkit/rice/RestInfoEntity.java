package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>RestInfoEntity</code>
 * <p>The type rest info entity class.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestInfoEntity} <p>the generic parameter is <code>RestInfoEntity</code> type.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestInfoModel} <p>the generic parameter is <code>RestInfoModel</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoModel
 * @see io.github.nichetoolkit.rice.DefaultInfoEntity
 * @since Jdk1.8
 */
public abstract class RestInfoEntity<E extends RestInfoEntity<E, M>, M extends RestInfoModel<M, E>> extends DefaultInfoEntity<E, M, String> {

    /**
     * <code>RestInfoEntity</code>
     * Instantiates a new rest info entity.
     */
    public RestInfoEntity() {
    }

    /**
     * <code>RestInfoEntity</code>
     * Instantiates a new rest info entity.
     * @param id {@link java.lang.String} <p>the id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestInfoEntity(String id) {
        super(id);
    }

    /**
     * <code>RestInfoEntity</code>
     * Instantiates a new rest info entity.
     * @param id   {@link java.lang.String} <p>the id parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestInfoEntity(String id, String name) {
        super(id, name);
    }

    /**
     * <code>RestInfoEntity</code>
     * Instantiates a new rest info entity.
     * @param builder {@link io.github.nichetoolkit.rice.RestInfoEntity.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestInfoEntity.Builder
     */
    public RestInfoEntity(Builder<E, M> builder) {
        super(builder);
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <E> {@link io.github.nichetoolkit.rice.RestInfoEntity} <p>the generic parameter is <code>RestInfoEntity</code> type.</p>
     * @param <M> {@link io.github.nichetoolkit.rice.RestInfoModel} <p>the generic parameter is <code>RestInfoModel</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.RestInfoModel
     * @see io.github.nichetoolkit.rice.DefaultInfoEntity.Builder
     * @since Jdk1.8
     */
    public static abstract class Builder<E extends RestInfoEntity<E, M>, M extends RestInfoModel<M, E>> extends DefaultInfoEntity.Builder<E, M, String> {

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        @Override
        public RestInfoEntity.Builder<E, M> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public RestInfoEntity.Builder<E, M> logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        @Override
        public abstract RestInfoEntity<E, M> build();
    }
}
