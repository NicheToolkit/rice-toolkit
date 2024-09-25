package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.SaveType;

/**
 * <code>LogicModel</code>
 * <p>The type logic model class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.SaveModel
 * @see io.github.nichetoolkit.rice.RestLogic
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogicModel extends SaveModel implements RestLogic {

    /**
     * <code>logic</code>
     * {@link java.lang.String} <p>the <code>logic</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    protected String logic;

    /**
     * <code>LogicModel</code>
     * Instantiates a new logic model.
     */
    public LogicModel() {
    }

    /**
     * <code>LogicModel</code>
     * Instantiates a new logic model.
     * @param builder {@link io.github.nichetoolkit.rice.LogicModel.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.LogicModel.Builder
     */
    public LogicModel(LogicModel.Builder builder) {
        super(builder);
        this.logic = builder.logic;
    }


    @Override
    public String getLogic() {
        return logic;
    }

    @Override
    public void setLogic(String logic) {
        this.logic = logic;
    }

    /**
     * <code>ofLogic</code>
     * <p>the logic method.</p>
     * @return {@link io.github.nichetoolkit.rice.LogicModel.Builder} <p>the logic return object is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.LogicModel.Builder
     */
    public static LogicModel.Builder ofLogic() {
        return new LogicModel.Builder();
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
         * <code>logic</code>
         * {@link java.lang.String} <p>the <code>logic</code> field.</p>
         * @see java.lang.String
         */
        protected String logic;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>logic</code>
         * <p>the method.</p>
         * @param logic {@link java.lang.String} <p>the logic parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.LogicModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
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
