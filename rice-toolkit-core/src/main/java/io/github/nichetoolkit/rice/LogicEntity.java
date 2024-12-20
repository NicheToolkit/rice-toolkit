package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.column.RestLogicKey;
import lombok.Getter;
import lombok.Setter;

/**
 * <code>LogicEntity</code>
 * <p>The logic entity class.</p>
 * @see  io.github.nichetoolkit.rice.RestLogic
 * @see  lombok.Setter
 * @see  lombok.Getter
 * @see  java.lang.SuppressWarnings
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Setter
@Getter
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogicEntity implements RestLogic {

    /**
     * <code>logic</code>
     * {@link java.lang.Object} <p>The <code>logic</code> field.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.mybatis.column.RestLogicKey
     */
    @RestLogicKey
    protected Object logic;

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
     * @see  io.github.nichetoolkit.rice.LogicEntity.Builder
     */
    public LogicEntity(LogicEntity.Builder builder) {
        this.logic = builder.logic;
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @see  java.lang.SuppressWarnings
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    @SuppressWarnings("WeakerAccess")
    public static class Builder {
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
         * @return  {@link io.github.nichetoolkit.rice.LogicEntity.Builder} <p>The logic return object is <code>Builder</code> type.</p>
         */
        public LogicEntity.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        /**
         * <code>build</code>
         * <p>The build method.</p>
         * @return  {@link io.github.nichetoolkit.rice.LogicEntity} <p>The build return object is <code>LogicEntity</code> type.</p>
         */
        public LogicEntity build() {
            return new LogicEntity(this);
        }
    }
}
