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
 * @see io.github.nichetoolkit.rice.LogicEntity
 * @see io.github.nichetoolkit.rice.RestOperate
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateEntity extends LogicEntity {

    /**
     * <code>operate</code>
     * {@link java.lang.Integer} <p>The <code>operate</code> field.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestOperateKey
     */
    @RestOperateKey
    protected Integer operate;

    /**
     * <code>OperateEntity</code>
     * <p>Instantiates a new operate entity.</p>
     */
    public OperateEntity() {
    }

    /**
     * <code>OperateEntity</code>
     * <p>Instantiates a new operate entity.</p>
     * @param builder {@link io.github.nichetoolkit.rice.OperateEntity.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.OperateEntity.Builder
     */
    public OperateEntity(OperateEntity.Builder builder) {
        super(builder);
        this.operate = builder.operate;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.LogicEntity.Builder
     * @see java.lang.SuppressWarnings
     * @since Jdk1.8
     */
    @SuppressWarnings("WeakerAccess")
    public static class Builder extends LogicEntity.Builder {
        /**
         * <code>operate</code>
         * {@link java.lang.Integer} <p>The <code>operate</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer operate;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>operate</code>
         * <p>The method.</p>
         * @param operate {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.OperateEntity.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public OperateEntity.Builder operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        /**
         * <code>operate</code>
         * <p>The method.</p>
         * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.OperateEntity.Builder} <p>The return object is <code>Builder</code> type.</p>
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

        @Override
        public OperateEntity build() {
            return new OperateEntity(this);
        }
    }
}
