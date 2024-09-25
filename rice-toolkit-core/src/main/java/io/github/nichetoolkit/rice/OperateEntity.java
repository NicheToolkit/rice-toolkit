package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.stereotype.column.RestOperateKey;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

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
public class OperateEntity extends LogicEntity implements RestOperate<Integer> {

    /**
     * <code>operate</code>
     * {@link java.lang.Integer} <p>the <code>operate</code> field.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestOperateKey
     */
    @RestOperateKey
    protected Integer operate;

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
        super(builder);
        this.operate = builder.operate;
    }

    /**
     * <code>getOperate</code>
     * <p>the operate getter method.</p>
     * @return {@link java.lang.Integer} <p>the operate return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    @Override
    public Integer getOperate() {
        return operate;
    }

    /**
     * <code>setOperate</code>
     * <p>the operate setter method.</p>
     * @param operate {@link java.lang.Integer} <p>the operate parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    @Override
    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.lang.SuppressWarnings
     * @since Jdk1.8
     */
    @SuppressWarnings("WeakerAccess")
    public static class Builder extends LogicEntity.Builder {
        /**
         * <code>operate</code>
         * {@link java.lang.Integer} <p>the <code>operate</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer operate;

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

        @Override
        public OperateEntity.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rice.OperateEntity} <p>the return object is <code>OperateEntity</code> type.</p>
         */
        @Override
        public OperateEntity build() {
            return new OperateEntity(this);
        }
    }
}
