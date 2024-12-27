package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.SaveType;

/**
 * <code>LogicModel</code>
 * <p>The logic model class.</p>
 * @see  io.github.nichetoolkit.rice.SaveModel
 * @see  io.github.nichetoolkit.rice.RestLogic
 * @see  java.lang.SuppressWarnings
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogicModel extends SaveModel implements RestLogic {

    /**
     * <code>logic</code>
     * {@link java.lang.Object} <p>The <code>logic</code> field.</p>
     * @see  java.lang.Object
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    protected Object logic;

    /**
     * <code>LogicModel</code>
     * <p>Instantiates a new logic model.</p>
     */
    public LogicModel() {
    }

    /**
     * <code>LogicModel</code>
     * <p>Instantiates a new logic model.</p>
     * @param builder {@link io.github.nichetoolkit.rice.LogicModel.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.LogicModel.Builder
     */
    public LogicModel(LogicModel.Builder builder) {
        super(builder);
        this.logic = builder.logic;
    }


    @Override
    public Object getLogic() {
        return logic;
    }

    @Override
    public void setLogic(Object logic) {
        this.logic = logic;
    }

    /**
     * <code>ofLogic</code>
     * <p>The of logic method.</p>
     * @return  {@link io.github.nichetoolkit.rice.LogicModel.Builder} <p>The of logic return object is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.LogicModel.Builder
     */
    public static LogicModel.Builder ofLogic() {
        return new LogicModel.Builder();
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @see  io.github.nichetoolkit.rice.SaveModel.Builder
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder extends SaveModel.Builder {
        /**
         * <code>logic</code>
         * {@link java.lang.String} <p>The <code>logic</code> field.</p>
         * @see  java.lang.String
         */
        protected String logic;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>logic</code>
         * <p>The logic method.</p>
         * @param logic {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link io.github.nichetoolkit.rice.LogicModel.Builder} <p>The logic return object is <code>Builder</code> type.</p>
         */
        public LogicModel.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public LogicModel.Builder save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public LogicModel.Builder save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public LogicModel build() {
            return new LogicModel(this);
        }
    }
}
