package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.filter.IdFilter;

/**
 * <code>FilterAdvice</code>
 * <p>The type filter advice interface.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>the generic parameter is <code>IdFilter</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface FilterAdvice<K, I, F extends IdFilter<I, K>> {

    /**
     * <code>applyHandle</code>
     * <p>the handle method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void applyHandle() throws RestException {
    }

    /**
     * <code>queryWhereSql</code>
     * <p>the where sql method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.String} <p>the where sql return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    default String queryWhereSql(F filter) throws RestException {
        return filter.toSql();
    }

    /**
     * <code>deleteWhereSql</code>
     * <p>the where sql method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.String} <p>the where sql return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    default String deleteWhereSql(F filter) throws RestException {
        return filter.toIdSql().toDeleteSql();
    }

    /**
     * <code>removeWhereSql</code>
     * <p>the where sql method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.String} <p>the where sql return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    default String removeWhereSql(F filter) throws RestException {
        return deleteWhereSql(filter);
    }

    /**
     * <code>operateWhereSql</code>
     * <p>the where sql method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.String} <p>the where sql return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    default String operateWhereSql(F filter) throws RestException {
        return deleteWhereSql(filter);
    }

    /**
     * <code>findLoadArray</code>
     * <p>the load array method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.Boolean} <p>the load array return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    default Boolean[] findLoadArray(F filter) throws RestException {
        return filter.toLoadArray();
    }

    /**
     * <code>queryLoadArray</code>
     * <p>the load array method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.Boolean} <p>the load array return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    default Boolean[] queryLoadArray(F filter) throws RestException {
        return filter.toLoadArray();
    }

    /**
     * <code>fieldArray</code>
     * <p>the array method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.String} <p>the array return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    default String[] fieldArray(F filter) throws RestException {
        return filter.toFieldArray();
    }

    /**
     * <code>tablekey</code>
     * <p>the method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @return K <p>the return object is <code>K</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default K tablekey(F filter) throws RestException {
        return filter.toTablekey();
    }
}
