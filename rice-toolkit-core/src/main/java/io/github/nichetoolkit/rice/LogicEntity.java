package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.stereotype.column.RestLogicKey;

/**
 * <code>LogicEntity</code>
 * <p>The logic entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogicEntity {

    /**
     * <code>logic</code>
     * {@link java.lang.String} <p>The <code>logic</code> field.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestLogicKey
     */
    @RestLogicKey
    protected String logic;

    /**
     * <code>LogicEntity</code>
     * <p>Instantiates a new logic entity.</p>
     */
    public LogicEntity() {
    }

    /**
     * <code>LogicEntity</code>
     * <p>Instantiates a new logic entity.</p>
     * @param builder {@link io.github.nichetoolkit.rice.LogicEntity.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.LogicEntity.Builder
     */
    public LogicEntity(LogicEntity.Builder builder) {
        this.logic = builder.logic;
    }

    /**
     * <code>getLogic</code>
     * <p>The get logic getter method.</p>
     * @return {@link java.lang.String} <p>The get logic return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getLogic() {
        return logic;
    }

    /**
     * <code>setLogic</code>
     * <p>The set logic setter method.</p>
     * @param logic {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setLogic(String logic) {
        this.logic = logic;
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.lang.SuppressWarnings
     * @since Jdk1.8
     */
    @SuppressWarnings("WeakerAccess")
    public static class Builder {
        /**
         * <code>logic</code>
         * {@link java.lang.String} <p>The <code>logic</code> field.</p>
         * @see java.lang.String
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
         * @return {@link io.github.nichetoolkit.rice.LogicEntity.Builder} <p>The logic return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public LogicEntity.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        /**
         * <code>build</code>
         * <p>The build method.</p>
         * @return {@link io.github.nichetoolkit.rice.LogicEntity} <p>The build return object is <code>LogicEntity</code> type.</p>
         */
        public LogicEntity build() {
            return new LogicEntity(this);
        }
    }
}
