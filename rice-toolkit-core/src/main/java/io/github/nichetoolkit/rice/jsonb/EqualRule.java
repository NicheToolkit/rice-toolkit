package io.github.nichetoolkit.rice.jsonb;

import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import java.util.Optional;

@Setter
@Getter
@SuppressWarnings("WeakerAccess")
@SuperBuilder
public class EqualRule extends JsonbRule<EqualRule> {
    protected ValueType type = ValueType.STRING;
    protected String value;
    protected EqualOperation operation = EqualOperation.EQUAL_OPERATION;

    public EqualRule() {
    }

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
