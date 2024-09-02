package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;

/**
 * <code>OperateModel</code>
 * <p>The type operate model class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.SaveModel
 * @see io.github.nichetoolkit.rice.RestOperate
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateModel extends SaveModel implements RestOperate{

    /**
     * <code>operate</code>
     * {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the <code>operate</code> field.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     */
    protected OperateType operate;

    /**
     * <code>OperateModel</code>
     * Instantiates a new operate model.
     */
    public OperateModel() {
    }

    /**
     * <code>OperateModel</code>
     * Instantiates a new operate model.
     * @param builder {@link io.github.nichetoolkit.rice.OperateModel.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.OperateModel.Builder
     */
    public OperateModel(OperateModel.Builder builder) {
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
     * <p>the operate none method.</p>
     * @return boolean <p>the operate none return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateNone() {
        return this.operate == OperateType.NONE;
    }

    /**
     * <code>isOperateCreate</code>
     * <p>the operate create method.</p>
     * @return boolean <p>the operate create return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateCreate() {
        return this.operate == OperateType.INSERT;
    }

    /**
     * <code>isOperateUpdate</code>
     * <p>the operate update method.</p>
     * @return boolean <p>the operate update return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateUpdate() {
        return this.operate == OperateType.UPDATE;
    }

    /**
     * <code>isOperateCopy</code>
     * <p>the operate copy method.</p>
     * @return boolean <p>the operate copy return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateCopy() {
        return this.operate == OperateType.COPY;
    }

    /**
     * <code>isOperateRemove</code>
     * <p>the operate remove method.</p>
     * @return boolean <p>the operate remove return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateRemove() {
        return this.operate == OperateType.REMOVE;
    }

    /**
     * <code>isOperateDelete</code>
     * <p>the operate delete method.</p>
     * @return boolean <p>the operate delete return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateDelete() {
        return this.operate == OperateType.DELETE;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.SaveModel.Builder
     * @since Jdk1.8
     */
    public static class Builder extends SaveModel.Builder {
        /**
         * <code>operate</code>
         * {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the <code>operate</code> field.</p>
         * @see io.github.nichetoolkit.rice.enums.OperateType
         */
        protected OperateType operate = OperateType.NONE;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>operate</code>
         * <p>the method.</p>
         * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.OperateModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.enums.OperateType
         */
        public OperateModel.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        /**
         * <code>operate</code>
         * <p>the method.</p>
         * @param operate {@link java.lang.Integer} <p>the operate parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.OperateModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public OperateModel.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
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

        public OperateModel build() {
            return new OperateModel(this);
        }
    }
}
