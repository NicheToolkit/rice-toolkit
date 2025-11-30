package io.github.nichetoolkit.rice.jsonb;

import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

/**
 * <code>EqualRule</code>
 * <p>The equal rule class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.jsonb.JsonbRule
 * @see lombok.Setter
 * @see lombok.Getter
 * @see java.lang.SuppressWarnings
 * @see lombok.experimental.SuperBuilder
 * @since Jdk17
 */
@Setter
@Getter
@SuppressWarnings("WeakerAccess")
@SuperBuilder
public class EqualRule extends JsonbRule<EqualRule> {
    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected ValueType type = ValueType.STRING;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see java.lang.String
     */
    protected String value;
    /**
     * <code>operation</code>
     * {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The <code>operation</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.EqualOperation
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected EqualOperation operation = EqualOperation.EQUAL_OPERATION;

    /**
     * <code>EqualRule</code>
     * <p>Instantiates a new equal rule.</p>
     */
    public EqualRule() {
    }

    /**
     * <code>EqualRule</code>
     * <p>Instantiates a new equal rule.</p>
     * @param name      {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type      {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
     * @param value     {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param operation {@link io.github.nichetoolkit.rice.jsonb.EqualOperation} <p>The operation parameter is <code>EqualOperation</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     * @see io.github.nichetoolkit.rice.jsonb.EqualOperation
     */
    public EqualRule(String name, ValueType type, String value, EqualOperation operation) {
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
        if (GeneralUtils.isEmpty(this.type) || GeneralUtils.isEmpty(this.operation)) {
            return SqlBuilder.EMPTY;
        }
        if (EqualOperation.NOT_NULL_OPERATION != this.operation && GeneralUtils.isEmpty(this.value)) {
            return SqlBuilder.EMPTY;
        }
        SqlBuilder sqlBuilder = SqlBuilders.newSqlBuilder();
        if (ValueType.isEqual(this.type.getKey())) {
            String target = target(alias, this.name, variable, this.type);
            String sql = this.operation.translateSql(target, this.value);
            sqlBuilder.andOfOr(true).append(sql);
        }
        return sqlBuilder.toString();
    }
}
