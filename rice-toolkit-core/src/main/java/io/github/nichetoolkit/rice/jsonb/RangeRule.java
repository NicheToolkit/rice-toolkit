package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

/**
 * <code>RangeRule</code>
 * <p>The range rule class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.jsonb.JsonbRule
 * @see lombok.Setter
 * @see lombok.Getter
 * @see java.lang.SuppressWarnings
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Setter
@Getter
@SuppressWarnings("WeakerAccess")
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RangeRule extends JsonbRule<RangeRule> {

    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     */
    protected ValueType type = ValueType.STRING;
    /**
     * <code>startValue</code>
     * {@link java.lang.Object} <p>The <code>startValue</code> field.</p>
     * @see java.lang.Object
     */
    protected Object startValue;
    /**
     * <code>endValue</code>
     * {@link java.lang.Object} <p>The <code>endValue</code> field.</p>
     * @see java.lang.Object
     */
    protected Object endValue;
    /**
     * <code>operation</code>
     * {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The <code>operation</code> field.</p>
     * @see io.github.nichetoolkit.rice.jsonb.RangeOperation
     */
    protected RangeOperation operation = RangeOperation.GREATER_EQUAL_LESS_EQUAL_OPERATION;


    /**
     * <code>RangeRule</code>
     * <p>Instantiates a new range rule.</p>
     */
    public RangeRule() {
    }

    /**
     * <code>RangeRule</code>
     * <p>Instantiates a new range rule.</p>
     * @param name       {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type       {@link io.github.nichetoolkit.rice.jsonb.ValueType} <p>The type parameter is <code>ValueType</code> type.</p>
     * @param startValue {@link java.lang.Object} <p>The start value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @param operation  {@link io.github.nichetoolkit.rice.jsonb.RangeOperation} <p>The operation parameter is <code>RangeOperation</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.jsonb.ValueType
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rice.jsonb.RangeOperation
     */
    public RangeRule(String name, ValueType type, Object startValue, Object endValue, RangeOperation operation) {
        super(name);
        this.type = type;
        this.startValue = startValue;
        this.endValue = endValue;
        this.operation = operation;
    }

    @Override
    public String toSql(@NonNull String alias) {
        return toSql(alias,"value");
    }

    @Override
    public String toSql(@NonNull String alias, @NonNull String variable) {
        if (GeneralUtils.isEmpty(this.startValue) || GeneralUtils.isEmpty(this.endValue)
                || GeneralUtils.isEmpty(this.type) || GeneralUtils.isEmpty(this.operation)) {
            return SqlBuilder.EMPTY;
        }
        SqlBuilder sqlBuilder = SqlBuilders.newSqlBuilder();
        if (ValueType.isRange(this.type.getKey())) {
            String target = target(alias, this.name, variable, this.type);
            String sql = this.operation.translateSql(target, this.startValue,this.endValue);
            sqlBuilder.andOfOr(true).append(sql);
        }
        return sqlBuilder.toString();
    }
}
