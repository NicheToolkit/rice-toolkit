package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * <code>JsonbRule</code>
 * <p>The type jsonb rule class.</p>
 * @param <R> {@link io.github.nichetoolkit.rice.jsonb.JsonbRule} <p>the generic parameter is <code>JsonbRule</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class JsonbRule<R extends JsonbRule<R>> implements Serializable {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>the <code>name</code> field.</p>
     * @see java.lang.String
     */
    String name;

    /**
     * <code>JsonbRule</code>
     * Instantiates a new jsonb rule.
     */
    JsonbRule() {
    }

    /**
     * <code>JsonbRule</code>
     * Instantiates a new jsonb rule.
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    JsonbRule(String name) {
        this.name = name;
    }

    /**
     * <code>JsonbRule</code>
     * Instantiates a new jsonb rule.
     * @param builder {@link io.github.nichetoolkit.rice.jsonb.JsonbRule.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.jsonb.JsonbRule.Builder
     */
    JsonbRule(JsonbRule.Builder<R> builder) {
        this.name = builder.name;
    }

    /**
     * <code>toSql</code>
     * <p>the sql method.</p>
     * @param alias {@link java.lang.String} <p>the alias parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the sql return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    abstract public String toSql(@NonNull String alias);

    /**
     * <code>toSql</code>
     * <p>the sql method.</p>
     * @param alias    {@link java.lang.String} <p>the alias parameter is <code>String</code> type.</p>
     * @param variable {@link java.lang.String} <p>the variable parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the sql return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    abstract public String toSql(@NonNull String alias, @NonNull String variable);

    /**
     * <code>target</code>
     * <p>the method.</p>
     * @param target {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param name   {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @param type   {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>the type parameter is <code>ValueType</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    String target(String target, String name, String value, ValueType type) {
        return target(target,name,value,type.getField());
    }

    private String target(String target, String name, String value, String type) {
        SqlBuilder targetBuilder = SqlBuilders.newSqlBuilder();
        if (GeneralUtils.isNotEmpty(name) && GeneralUtils.isNotEmpty(value)) {
            targetBuilder.append("((").append(target);
            targetBuilder.append(" #>'{").append(name);
            targetBuilder.append("}') ->>'").append(value);
            targetBuilder.append("')::").append(type);
        }
        return targetBuilder.toString();
    }

    /**
     * <code>getName</code>
     * <p>the name getter method.</p>
     * @return {@link java.lang.String} <p>the name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getName() {
        return name;
    }

    /**
     * <code>setName</code>
     * <p>the name setter method.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <R> {@link io.github.nichetoolkit.rice.jsonb.JsonbRule} <p>the generic parameter is <code>JsonbRule</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public abstract static class Builder<R extends JsonbRule<R>> {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>the <code>name</code> field.</p>
         * @see java.lang.String
         */
        String name;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>name</code>
         * <p>the method.</p>
         * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.jsonb.JsonbRule.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public JsonbRule.Builder<R> name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return R <p>the return object is <code>R</code> type.</p>
         */
        abstract public R build();
    }
}
