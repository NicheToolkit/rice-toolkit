package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.mybatis.builder.SqlUtils;
import io.github.nichetoolkit.mybatis.enums.StyleType;
import io.github.nichetoolkit.mybatis.stereotype.table.RestAlertness;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.*;

public interface StatusFilter<S> extends Serializable {

    S getStatus();

    void setStatus(S status);

    List<S> getStatuses();

    @JsonSetter
    void setStatuses(@NonNull Collection<S> statuses);

    @SuppressWarnings(value = "unchecked")
    void setStatuses(@NonNull S... statuses);

    default List<S> toStatuses() {
        Set<S> statusesSet = new HashSet<>();
        if (GeneralUtils.isNotEmpty(getStatus())) {
            statusesSet.add(getStatus());
        }
        if (GeneralUtils.isNotEmpty(getStatuses())) {
            statusesSet.addAll(getStatuses());
        }
        return new ArrayList<>(statusesSet);
    }

    StatusFilter<S> toStatusSql();

    StatusFilter<S> toStatusSql(@NonNull String alias);

    @SuppressWarnings("unchecked")
    default StatusFilter<S> toStatusSql(SqlBuilder sqlBuilder, @NonNull String alias) {
        Class<S> statusType = (Class<S>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                StatusFilter.class.getTypeParameters()[0], getClass(), StatusFilter.class));
        if (statusType.isAnnotationPresent(RestAlertness.class)) {
            List<S> statusList = toStatuses();
            String prefix = null;
            if (GeneralUtils.isNotEmpty(alias) && alias.contains(".")) {
                prefix = alias.split("\\.")[0];
            }
            String whereSqlOfTypes = SqlUtils.whereSqlOfTypes(prefix, statusList, statusType, StyleType.LOWER_UNDERLINE);
            SqlBuilders.append(sqlBuilder, whereSqlOfTypes);
        } else {
            if (GeneralUtils.isNotEmpty(getStatus())) {
                SqlBuilders.equal(sqlBuilder, alias, getStatus());
            } else if (GeneralUtils.isNotEmpty(getStatuses())) {
                SqlBuilders.in(sqlBuilder, alias, getStatuses());
            }
        }
        return this;
    }

    String toKey();

}
