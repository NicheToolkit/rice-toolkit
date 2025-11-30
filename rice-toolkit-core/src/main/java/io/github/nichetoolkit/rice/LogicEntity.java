package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.column.RestLogicKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * <code>LogicEntity</code>
 * <p>The logic entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestLogic
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
@SuperBuilder(builderMethodName = "ofLogicBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogicEntity implements RestLogic {

    /**
     * <code>logic</code>
     * {@link java.lang.Object} <p>The <code>logic</code> field.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.mybatis.column.RestLogicKey
     */
    @RestLogicKey
    protected Object logic;

    /**
     * <code>LogicEntity</code>
     * <p>Instantiates a new logic entity.</p>
     */
    public LogicEntity() {
    }
}
