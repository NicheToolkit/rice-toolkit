package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.mybatis.table.RestAlertness;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>StateFilter</code>
 * <p>The state filter interface.</p>
 * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.AlertFilter
 * @since Jdk1.8
 */
public interface StateFilter<S> extends AlertFilter<S> {

    /**
     * <code>getStates</code>
     * <p>The get states getter method.</p>
     * @return {@link java.util.List} <p>The get states return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    List<S> getStates();

    /**
     * <code>sqlBuilder</code>
     * <p>The sql builder method.</p>
     * @return {@link io.github.nichetoolkit.mybatis.builder.SqlBuilder} <p>The sql builder return object is <code>SqlBuilder</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.builder.SqlBuilder
     */
    SqlBuilder sqlBuilder();

    /**
     * <code>setStates</code>
     * <p>The set states setter method.</p>
     * @param states {@link java.util.Collection} <p>The states parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     * @see com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    void setStates(@NonNull Collection<S> states);

    /**
     * <code>toStates</code>
     * <p>The to states method.</p>
     * @return {@link java.util.List} <p>The to states return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    default List<S> toStates() {
        Set<S> stateesSet = new HashSet<>();
        if (GeneralUtils.isNotEmpty(getState())) {
            stateesSet.add(getState());
        }
        if (GeneralUtils.isNotEmpty(getStates())) {
            stateesSet.addAll(getStates());
        }
        return new ArrayList<>(stateesSet);
    }

    /**
     * <code>toStateSql</code>
     * <p>The to state sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.StateFilter} <p>The to state sql return object is <code>StateFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    default StateFilter<S> toStateSql(@NonNull String alias) throws RestException {
        toStateSql(sqlBuilder(), alias);
        return this;
    }

    /**
     * <code>toStateSql</code>
     * <p>The to state sql method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.mybatis.builder.SqlBuilder} <p>The sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param alias      {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.builder.SqlBuilder
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void toStateSql(SqlBuilder sqlBuilder, @NonNull String alias) throws RestException {
        if (getStateType().isAnnotationPresent(RestAlertness.class)) {
            toAlertnessSql(sqlBuilder, alias);
        } else {
            if (GeneralUtils.isNotEmpty(getState())) {
                SqlBuilders.equal(sqlBuilder, alias, getState());
            } else if (GeneralUtils.isNotEmpty(getStates())) {
                SqlBuilders.in(sqlBuilder, alias, getStates());
            }
        }
    }

    /**
     * <code>toAlertnessSql</code>
     * <p>The to alertness sql method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.mybatis.builder.SqlBuilder} <p>The sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param alias      {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.builder.SqlBuilder
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void toAlertnessSql(SqlBuilder sqlBuilder,@NonNull String alias) throws RestException {
        throw new UnsupportedErrorException("the method of 'toAlertnessSql()' is unsupported.");
    }

}
