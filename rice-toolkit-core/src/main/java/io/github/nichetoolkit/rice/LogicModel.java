package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.SuperBuilder;

/**
 * <code>LogicModel</code>
 * <p>The logic model class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.SaveModel
 * @see io.github.nichetoolkit.rice.RestLogic
 * @see lombok.experimental.SuperBuilder
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofLogicBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogicModel extends SaveModel implements RestLogic {

    /**
     * <code>logic</code>
     * {@link java.lang.Object} <p>The <code>logic</code> field.</p>
     * @see java.lang.Object
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    protected Object logic;

    /**
     * <code>LogicModel</code>
     * <p>Instantiates a new logic model.</p>
     */
    public LogicModel() {
    }

    @Override
    public Object getLogic() {
        return logic;
    }

    @Override
    public void setLogic(Object logic) {
        this.logic = logic;
    }

}
