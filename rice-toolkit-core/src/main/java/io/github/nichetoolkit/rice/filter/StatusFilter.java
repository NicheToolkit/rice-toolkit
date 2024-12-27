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

/**
 * <code>StatusFilter</code>
 * <p>The status filter interface.</p>
 * @param <S>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  java.io.Serializable
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface StatusFilter<S> extends Serializable {

    /**
     * <code>getStatus</code>
     * <p>The get status getter method.</p>
     * @return S <p>The get status return object is <code>S</code> type.</p>
     */
    S getStatus();

    /**
     * <code>setStatus</code>
     * <p>The set status setter method.</p>
     * @param status S <p>The status parameter is <code>S</code> type.</p>
     */
    void setStatus(S status);

    /**
     * <code>getStatuses</code>
     * <p>The get statuses getter method.</p>
     * @return  {@link java.util.List} <p>The get statuses return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    List<S> getStatuses();

    /**
     * <code>getStatusType</code>
     * <p>The get status type getter method.</p>
     * @return  {@link java.lang.Class} <p>The get status type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     * @see  java.lang.SuppressWarnings
     */
    @JsonIgnore
    @SuppressWarnings("unchecked")
    default Class<S> getStatusType() {
        return  (Class<S>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                StatusFilter.class.getTypeParameters()[0], getClass(), StatusFilter.class));
    }

    /**
     * <code>setStatuses</code>
     * <p>The set statuses setter method.</p>
     * @param statuses {@link java.util.Collection} <p>The statuses parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  org.springframework.lang.NonNull
     * @see  com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    void setStatuses(@NonNull Collection<S> statuses);

    /**
     * <code>setStatuses</code>
     * <p>The set statuses setter method.</p>
     * @param statuses S <p>The statuses parameter is <code>S</code> type.</p>
     * @see  org.springframework.lang.NonNull
     * @see  java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    void setStatuses(@NonNull S... statuses);

    /**
     * <code>toStatuses</code>
     * <p>The to statuses method.</p>
     * @return  {@link java.util.List} <p>The to statuses return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
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

    /**
     * <code>toStatusSql</code>
     * <p>The to status sql method.</p>
     * @return  {@link io.github.nichetoolkit.rice.filter.StatusFilter} <p>The to status sql return object is <code>StatusFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    StatusFilter<S> toStatusSql() throws RestException;

    /**
     * <code>toStatusSql</code>
     * <p>The to status sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rice.filter.StatusFilter} <p>The to status sql return object is <code>StatusFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    StatusFilter<S> toStatusSql(@NonNull String alias) throws RestException;

    /**
     * <code>toStatusSql</code>
     * <p>The to status sql method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.mybatis.builder.SqlBuilder} <p>The sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  io.github.nichetoolkit.mybatis.builder.SqlBuilder
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rice.filter.StatusFilter} <p>The to status sql return object is <code>StatusFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default StatusFilter<S> toStatusSql(SqlBuilder sqlBuilder, @NonNull String alias) throws RestException {
        if (getStatusType().isAnnotationPresent(RestAlertness.class)) {
            toAlertnessSql(sqlBuilder, alias);
        } else {
            if (GeneralUtils.isNotEmpty(getStatus())) {
                SqlBuilders.equal(sqlBuilder, alias, getStatus());
            } else if (GeneralUtils.isNotEmpty(getStatuses())) {
                SqlBuilders.in(sqlBuilder, alias, getStatuses());
            }
        }
        return this;
    }

    /**
     * <code>toAlertnessSql</code>
     * <p>The to alertness sql method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.mybatis.builder.SqlBuilder} <p>The sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  io.github.nichetoolkit.mybatis.builder.SqlBuilder
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rice.filter.StatusFilter} <p>The to alertness sql return object is <code>StatusFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default StatusFilter<S> toAlertnessSql(SqlBuilder sqlBuilder,@NonNull String alias) throws RestException {
        throw new UnsupportedErrorException("the method of 'toAlertnessSql()' is unsupported.");
    }

    /**
     * <code>toKey</code>
     * <p>The to key method.</p>
     * @return  {@link java.lang.String} <p>The to key return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String toKey();

}
