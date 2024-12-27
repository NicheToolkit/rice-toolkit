package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>RestInfoModel</code>
 * <p>The rest info model class.</p>
 * @param <M>  {@link io.github.nichetoolkit.rice.RestInfoModel} <p>The generic parameter is <code>RestInfoModel</code> type.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestInfoEntity} <p>The generic parameter is <code>RestInfoEntity</code> type.</p>
 * @see  io.github.nichetoolkit.rice.RestInfoEntity
 * @see  io.github.nichetoolkit.rice.DefaultInfoModel
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public abstract class RestInfoModel<M extends RestInfoModel<M, E>, E extends RestInfoEntity<E, M>> extends DefaultInfoModel<M, E, String> {

    /**
     * <code>RestInfoModel</code>
     * <p>Instantiates a new rest info model.</p>
     */
    public RestInfoModel() {
    }

    /**
     * <code>RestInfoModel</code>
     * <p>Instantiates a new rest info model.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public RestInfoModel(String id) {
        super(id);
    }

    /**
     * <code>RestInfoModel</code>
     * <p>Instantiates a new rest info model.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param description {@link java.lang.String} <p>The description parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public RestInfoModel(String name, String description) {
        super(name, description);
    }

    /**
     * <code>RestInfoModel</code>
     * <p>Instantiates a new rest info model.</p>
     * @param builder {@link io.github.nichetoolkit.rice.RestInfoModel.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.RestInfoModel.Builder
     */
    public RestInfoModel(RestInfoModel.Builder<M, E> builder) {
        super(builder);
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @param <M>  {@link io.github.nichetoolkit.rice.RestInfoModel} <p>The generic parameter is <code>RestInfoModel</code> type.</p>
     * @param <E>  {@link io.github.nichetoolkit.rice.RestInfoEntity} <p>The generic parameter is <code>RestInfoEntity</code> type.</p>
     * @see  io.github.nichetoolkit.rice.RestInfoEntity
     * @see  io.github.nichetoolkit.rice.DefaultInfoModel.Builder
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static abstract class Builder<M extends RestInfoModel<M, E>, E extends RestInfoEntity<E, M>> extends DefaultInfoModel.Builder<M, E, String> {

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        @Override
        public RestInfoModel.Builder<M, E> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public RestInfoModel.Builder<M, E> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public abstract RestInfoModel<M, E> build();
    }
}
