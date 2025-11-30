package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jspecify.annotations.NonNull;

/**
 * <code>ContrastRule</code>
 * <p>The contrast rule class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.jsonb.JsonbRule
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
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContrastRule extends JsonbRule<ContrastRule> {
    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected ValueType type = ValueType.LONG;
    /**
     * <code>value</code>
     * {@link java.lang.Object} <p>The <code>value</code> field.</p>
     * @see java.lang.Object
     */
    protected Object value;
    /**
     * <code>operation</code>
     * {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>The <code>operation</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ContrastOperation
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected ContrastOperation operation = ContrastOperation.EQUAL_OPERATION;

    /**
     * <code>ContrastRule</code>
     * <p>Instantiates a new contrast rule.</p>
     */
    public ContrastRule() {
    }

    /**
     * <code>ContrastRule</code>
     * <p>Instantiates a new contrast rule.</p>
     * @param name      {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type      {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
     * @param value     {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param operation {@link io.github.nichetoolkit.rice.jsonb.ContrastOperation} <p>The operation parameter is <code>ContrastOperation</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rice.jsonb.ContrastOperation
     */
    public ContrastRule(String name, ValueType type, Object value, ContrastOperation operation) {
        super(name);
        this.type = type;
        this.value = value;
        this.operation = operation;
    }

    @Override
    public String toSql(@NonNull String alias) {
        return toSql(alias,"value");
    }

    @Override
    @SuppressWarnings("Duplicates")
    public String toSql(@NonNull String alias, @NonNull String variable) {
        if (GeneralUtils.isEmpty(this.value) || GeneralUtils.isEmpty(this.type) || GeneralUtils.isEmpty(this.operation)) {
            return SqlBuilder.EMPTY;
        }
        SqlBuilder sqlBuilder = SqlBuilders.newSqlBuilder();
        if (ValueType.isContrast(this.type.getKey())) {
            String target = target(alias, this.name, variable, this.type);
            String sql = this.operation.translateSql(target, this.value);
            sqlBuilder.andOfOr(true).append(sql);
        }
        return sqlBuilder.toString();
    }
}
