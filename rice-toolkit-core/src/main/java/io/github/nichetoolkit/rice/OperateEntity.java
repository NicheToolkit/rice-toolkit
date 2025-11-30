package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.column.RestOperateKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * <code>OperateEntity</code>
 * <p>The operate entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.LogicEntity
 * @see lombok.Setter
 * @see lombok.Getter
 * @see java.lang.SuppressWarnings
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Setter
@Getter
@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofOperateBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateEntity extends LogicEntity {

    /**
     * <code>operate</code>
     * {@link java.lang.Integer} <p>The <code>operate</code> field.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.mybatis.column.RestOperateKey
     */
    @RestOperateKey
    protected Integer operate;

    /**
     * <code>OperateEntity</code>
     * <p>Instantiates a new operate entity.</p>
     */
    public OperateEntity() {
    }

}
