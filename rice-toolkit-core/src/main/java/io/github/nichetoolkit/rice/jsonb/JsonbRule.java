package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * <code>JsonbRule</code>
 * <p>The jsonb rule class.</p>
 * @param <R>  {@link io.github.nichetoolkit.rice.jsonb.JsonbRule} <p>The generic parameter is <code>JsonbRule</code> type.</p>
 * @see  java.io.Serializable
 * @see  lombok.Setter
 * @see  lombok.Getter
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Setter
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class JsonbRule<R extends JsonbRule<R>> implements Serializable {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>The <code>name</code> field.</p>
     * @see  java.lang.String
     */
    protected String name;

    /**
     * <code>JsonbRule</code>
     * <p>Instantiates a new jsonb rule.</p>
     */
    JsonbRule() {
    }

    /**
     * <code>JsonbRule</code>
     * <p>Instantiates a new jsonb rule.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    JsonbRule(String name) {
        this.name = name;
    }

    /**
     * <code>JsonbRule</code>
     * <p>Instantiates a new jsonb rule.</p>
     * @param builder {@link io.github.nichetoolkit.rice.jsonb.JsonbRule.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.jsonb.JsonbRule.Builder
     */
    JsonbRule(JsonbRule.Builder<R> builder) {
        this.name = builder.name;
    }

    /**
     * <code>toSql</code>
     * <p>The to sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @return  {@link java.lang.String} <p>The to sql return object is <code>String</code> type.</p>
     */
    abstract public String toSql(@NonNull String alias);

    /**
     * <code>toSql</code>
     * <p>The to sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @param variable {@link java.lang.String} <p>The variable parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @return  {@link java.lang.String} <p>The to sql return object is <code>String</code> type.</p>
     */
    abstract public String toSql(@NonNull String alias, @NonNull String variable);

    /**
     * <code>target</code>
     * <p>The target method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param type {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rice.jsonb.ValueType
     * @return  {@link java.lang.String} <p>The target return object is <code>String</code> type.</p>
     */
    String target(String target, String name, String value, ValueType type) {
        return target(target,name,value,type.getField());
    }

    /**
     * <code>target</code>
     * <p>The target method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param type {@link java.lang.String} <p>The type parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The target return object is <code>String</code> type.</p>
     */
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
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @param <R>  {@link io.github.nichetoolkit.rice.jsonb.JsonbRule} <p>The generic parameter is <code>JsonbRule</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public abstract static class Builder<R extends JsonbRule<R>> {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>The <code>name</code> field.</p>
         * @see  java.lang.String
         */
        String name;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>name</code>
         * <p>The name method.</p>
         * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link io.github.nichetoolkit.rice.jsonb.JsonbRule.Builder} <p>The name return object is <code>Builder</code> type.</p>
         */
        public JsonbRule.Builder<R> name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>build</code>
         * <p>The build method.</p>
         * @return R <p>The build return object is <code>R</code> type.</p>
         */
        abstract public R build();
    }
}
