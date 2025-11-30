package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import lombok.experimental.SuperBuilder;

/**
 * <code>OperateModel</code>
 * <p>The operate model class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.LogicModel
 * @see io.github.nichetoolkit.rice.RestOperate
 * @see java.lang.SuppressWarnings
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofOperateBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateModel extends LogicModel implements RestOperate{

    /**
     * <code>operate</code>
     * {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The <code>operate</code> field.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     */
    protected OperateType operate;

    /**
     * <code>OperateModel</code>
     * <p>Instantiates a new operate model.</p>
     */
    public OperateModel() {
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
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateNone() {
        return this.operate == OperateType.NONE;
    }

    /**
     * <code>isOperateCreate</code>
     * <p>The is operate create method.</p>
     * @return boolean <p>The is operate create return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateCreate() {
        return this.operate == OperateType.INSERT;
    }

    /**
     * <code>isOperateUpdate</code>
     * <p>The is operate update method.</p>
     * @return boolean <p>The is operate update return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateUpdate() {
        return this.operate == OperateType.UPDATE;
    }

    /**
     * <code>isOperateCopy</code>
     * <p>The is operate copy method.</p>
     * @return boolean <p>The is operate copy return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateCopy() {
        return this.operate == OperateType.COPY;
    }

    /**
     * <code>isOperateRemove</code>
     * <p>The is operate remove method.</p>
     * @return boolean <p>The is operate remove return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateRemove() {
        return this.operate == OperateType.REMOVE;
    }

    /**
     * <code>isOperateDelete</code>
     * <p>The is operate delete method.</p>
     * @return boolean <p>The is operate delete return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isOperateDelete() {
        return this.operate == OperateType.DELETE;
    }

}
