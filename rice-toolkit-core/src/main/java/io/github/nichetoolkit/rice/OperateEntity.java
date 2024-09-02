package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.mybatis.stereotype.column.RestLogic;
import io.github.nichetoolkit.mybatis.stereotype.column.RestOperate;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * <code>OperateEntity</code>
 * <p>The type operate entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateEntity implements Serializable {

    /**
     * <code>operate</code>
     * {@link java.lang.Integer} <p>the <code>operate</code> field.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestOperate
     */
    @RestOperate
    protected Integer operate;

    /**
     * <code>logicSign</code>
     * {@link java.lang.String} <p>the <code>logicSign</code> field.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestLogic
     */
    @RestLogic
    protected String logicSign;

    /**
     * <code>OperateEntity</code>
     * Instantiates a new operate entity.
     */
    public OperateEntity() {
    }

    /**
     * <code>OperateEntity</code>
     * Instantiates a new operate entity.
     * @param builder {@link io.github.nichetoolkit.rice.OperateEntity.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.OperateEntity.Builder
     */
    public OperateEntity(OperateEntity.Builder builder) {
        this.operate = builder.operate;
        this.logicSign = builder.logicSign;
    }

    /**
     * <code>getOperate</code>
     * <p>the operate getter method.</p>
     * @return {@link java.lang.Integer} <p>the operate return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer getOperate() {
        return operate;
    }

    /**
     * <code>setOperate</code>
     * <p>the operate setter method.</p>
     * @param operate {@link java.lang.Integer} <p>the operate parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    /**
     * <code>getLogicSign</code>
     * <p>the logic sign getter method.</p>
     * @return {@link java.lang.String} <p>the logic sign return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getLogicSign() {
        return logicSign;
    }

    /**
     * <code>setLogicSign</code>
     * <p>the logic sign setter method.</p>
     * @param logicSign {@link java.lang.String} <p>the logic sign parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setLogicSign(String logicSign) {
        this.logicSign = logicSign;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.lang.SuppressWarnings
     * @since Jdk1.8
     */
    @SuppressWarnings("WeakerAccess")
    public static class Builder {
        /**
         * <code>operate</code>
         * {@link java.lang.Integer} <p>the <code>operate</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer operate;
        /**
         * <code>logicSign</code>
         * {@link java.lang.String} <p>the <code>logicSign</code> field.</p>
         * @see java.lang.String
         */
        protected String logicSign;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>operate</code>
         * <p>the method.</p>
         * @param operate {@link java.lang.Integer} <p>the operate parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.OperateEntity.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public OperateEntity.Builder operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        /**
         * <code>operate</code>
         * <p>the method.</p>
         * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.OperateEntity.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.enums.OperateType
         * @see org.springframework.lang.NonNull
         */
        public OperateEntity.Builder operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        /**
         * <code>logicSign</code>
         * <p>the sign method.</p>
         * @param logicSign {@link java.lang.String} <p>the logic sign parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.OperateEntity.Builder} <p>the sign return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public OperateEntity.Builder logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rice.OperateEntity} <p>the return object is <code>OperateEntity</code> type.</p>
         */
        public OperateEntity build() {
            return new OperateEntity(this);
        }
    }
}
