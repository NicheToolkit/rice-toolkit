package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.mybatis.table.RestAlertness;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
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

    @JsonIgnore
    @SuppressWarnings("unchecked")
    default Class<S> getStatusType() {
        return  (Class<S>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                StatusFilter.class.getTypeParameters()[0], getClass(), StatusFilter.class));
    }

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

    StatusFilter<S> toStatusSql() throws RestException;

    StatusFilter<S> toStatusSql(@NonNull String alias) throws RestException;

    default void toStatusSql(SqlBuilder sqlBuilder, @NonNull String alias) throws RestException {
        if (getStatusType().isAnnotationPresent(RestAlertness.class)) {
            toAlertnessSql(sqlBuilder, alias);
        } else {
            if (GeneralUtils.isNotEmpty(getStatus())) {
                SqlBuilders.equal(sqlBuilder, alias, getStatus());
            } else if (GeneralUtils.isNotEmpty(getStatuses())) {
                SqlBuilders.in(sqlBuilder, alias, getStatuses());
            }
        }
    }

    default void toAlertnessSql(SqlBuilder sqlBuilder,@NonNull String alias) throws RestException {
        throw new UnsupportedErrorException("the method of 'toAlertnessSql()' is unsupported.");
    }

    String toKey();

}
