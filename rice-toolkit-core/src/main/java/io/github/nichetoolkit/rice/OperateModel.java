package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;

/**
 * <code>OperateModel</code>
 * <p>The operate model class.</p>
 * @see  io.github.nichetoolkit.rice.LogicModel
 * @see  io.github.nichetoolkit.rice.RestOperate
 * @see  java.lang.SuppressWarnings
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateModel extends LogicModel implements RestOperate{

    /**
     * <code>operate</code>
     * {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The <code>operate</code> field.</p>
     * @see  io.github.nichetoolkit.rice.enums.OperateType
     */
    protected OperateType operate;

    /**
     * <code>OperateModel</code>
     * <p>Instantiates a new operate model.</p>
     */
    public OperateModel() {
    }

    /**
     * <code>OperateModel</code>
     * <p>Instantiates a new operate model.</p>
     * @param builder {@link io.github.nichetoolkit.rice.OperateModel.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.OperateModel.Builder
     */
    public OperateModel(OperateModel.Builder builder) {
        super(builder);
        this.operate = builder.operate;
    }

    @Override
    public OperateType getOperate() {
        return operate;
    }

    @Override
    public void setOperate(OperateType operate) {
        this.operate = operate;
    }

    /**
     * <code>isOperateNone</code>
     * <p>The is operate none method.</p>
     * @return boolean <p>The is operate none return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateNone() {
        return this.operate == OperateType.NONE;
    }

    /**
     * <code>isOperateCreate</code>
     * <p>The is operate create method.</p>
     * @return boolean <p>The is operate create return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateCreate() {
        return this.operate == OperateType.INSERT;
    }

    /**
     * <code>isOperateUpdate</code>
     * <p>The is operate update method.</p>
     * @return boolean <p>The is operate update return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateUpdate() {
        return this.operate == OperateType.UPDATE;
    }

    /**
     * <code>isOperateCopy</code>
     * <p>The is operate copy method.</p>
     * @return boolean <p>The is operate copy return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateCopy() {
        return this.operate == OperateType.COPY;
    }

    /**
     * <code>isOperateRemove</code>
     * <p>The is operate remove method.</p>
     * @return boolean <p>The is operate remove return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateRemove() {
        return this.operate == OperateType.REMOVE;
    }

    /**
     * <code>isOperateDelete</code>
     * <p>The is operate delete method.</p>
     * @return boolean <p>The is operate delete return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateDelete() {
        return this.operate == OperateType.DELETE;
    }

    /**
     * <code>ofOperate</code>
     * <p>The of operate method.</p>
     * @return  {@link io.github.nichetoolkit.rice.OperateModel.Builder} <p>The of operate return object is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.OperateModel.Builder
     */
    public static OperateModel.Builder ofOperate() {
        return new OperateModel.Builder();
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @see  io.github.nichetoolkit.rice.LogicModel.Builder
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder extends LogicModel.Builder {
        /**
         * <code>operate</code>
         * {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The <code>operate</code> field.</p>
         * @see  io.github.nichetoolkit.rice.enums.OperateType
         */
        protected OperateType operate = OperateType.NONE;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>operate</code>
         * <p>The operate method.</p>
         * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
         * @see  io.github.nichetoolkit.rice.enums.OperateType
         * @return  {@link io.github.nichetoolkit.rice.OperateModel.Builder} <p>The operate return object is <code>Builder</code> type.</p>
         */
        public OperateModel.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        /**
         * <code>operate</code>
         * <p>The operate method.</p>
         * @param operate {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
         * @see  java.lang.Integer
         * @return  {@link io.github.nichetoolkit.rice.OperateModel.Builder} <p>The operate return object is <code>Builder</code> type.</p>
         */
        public OperateModel.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public OperateModel.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public OperateModel.Builder save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public OperateModel.Builder save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public OperateModel build() {
            return new OperateModel(this);
        }
    }
}
