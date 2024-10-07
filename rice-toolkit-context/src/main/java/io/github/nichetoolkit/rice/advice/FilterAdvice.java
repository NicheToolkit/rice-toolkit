package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.filter.IdFilter;

/**
 * <code>FilterAdvice</code>
 * <p>The type filter advice interface.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface FilterAdvice<F extends IdFilter<I, K>, I, K> {

    /**
     * <code>queryWhereSql</code>
     * <p>The where sql method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.String} <p>The where sql return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    default String queryWhereSql(F filter) throws RestException {
        return filter.toSql();
    }

    /**
     * <code>deleteWhereSql</code>
     * <p>The where sql method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.String} <p>The where sql return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    default String deleteWhereSql(F filter) throws RestException {
        return filter.toIdSql().toDeleteSql();
    }

    /**
     * <code>removeWhereSql</code>
     * <p>The where sql method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.String} <p>The where sql return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    default String removeWhereSql(F filter) throws RestException {
        return deleteWhereSql(filter);
    }

    /**
     * <code>operateWhereSql</code>
     * <p>The where sql method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.String} <p>The where sql return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    default String operateWhereSql(F filter) throws RestException {
        return deleteWhereSql(filter);
    }

    /**
     * <code>findLoadArray</code>
     * <p>The load array method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The load array return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    default Boolean[] findLoadArray(F filter) throws RestException {
        return filter.toLoadArray();
    }

    /**
     * <code>queryLoadArray</code>
     * <p>The load array method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The load array return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    default Boolean[] queryLoadArray(F filter) throws RestException {
        return filter.toLoadArray();
    }

    /**
     * <code>fieldArray</code>
     * <p>The array method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link java.lang.String} <p>The array return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    default String[] fieldArray(F filter) throws RestException {
        return filter.toFieldArray();
    }

    /**
     * <code>tablekey</code>
     * <p>The method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return K <p>The return object is <code>K</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default K tablekey(F filter) throws RestException {
        return filter.toTablekey();
    }
}
