package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.stereotype.column.RestLogicKey;

/**
 * <code>LogicEntity</code>
 * <p>The type logic entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestLogic
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogicEntity implements RestLogic {

    /**
     * <code>logic</code>
     * {@link java.lang.String} <p>the <code>logic</code> field.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestLogicKey
     */
    @RestLogicKey
    protected String logic;

    /**
     * <code>LogicEntity</code>
     * Instantiates a new logic entity.
     */
    public LogicEntity() {
    }

    /**
     * <code>LogicEntity</code>
     * Instantiates a new logic entity.
     * @param builder {@link io.github.nichetoolkit.rice.LogicEntity.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.LogicEntity.Builder
     */
    public LogicEntity(LogicEntity.Builder builder) {
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
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.lang.SuppressWarnings
     * @since Jdk1.8
     */
    @SuppressWarnings("WeakerAccess")
    public static class Builder {
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
         * @return {@link io.github.nichetoolkit.rice.LogicEntity.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public LogicEntity.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rice.LogicEntity} <p>the return object is <code>LogicEntity</code> type.</p>
         */
        public LogicEntity build() {
            return new LogicEntity(this);
        }
    }
}
