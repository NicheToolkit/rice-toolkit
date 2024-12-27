package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.filter.IdFilter;

/**
 * <code>FilterAdvice</code>
 * <p>The filter advice interface.</p>
 * @param <F>  {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.filter.IdFilter
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface FilterAdvice<F extends IdFilter<I, K>, I, K> {

    /**
     * <code>queryWhereSql</code>
     * <p>The query where sql method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return  {@link java.lang.String} <p>The query where sql return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default String queryWhereSql(F filter) throws RestException {
        return filter.toSql();
    }

    /**
     * <code>deleteWhereSql</code>
     * <p>The delete where sql method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return  {@link java.lang.String} <p>The delete where sql return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default String deleteWhereSql(F filter) throws RestException {
        return filter.toIdSql().toDeleteSql();
    }

    /**
     * <code>removeWhereSql</code>
     * <p>The remove where sql method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return  {@link java.lang.String} <p>The remove where sql return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default String removeWhereSql(F filter) throws RestException {
        return deleteWhereSql(filter);
    }

    /**
     * <code>operateWhereSql</code>
     * <p>The operate where sql method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return  {@link java.lang.String} <p>The operate where sql return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default String operateWhereSql(F filter) throws RestException {
        return deleteWhereSql(filter);
    }

    /**
     * <code>alertWhereSql</code>
     * <p>The alert where sql method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return  {@link java.lang.String} <p>The alert where sql return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default String alertWhereSql(F filter) throws RestException {
        return deleteWhereSql(filter);
    }

    /**
     * <code>findLoadArray</code>
     * <p>The find load array method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return  {@link java.lang.Boolean} <p>The find load array return object is <code>Boolean</code> type.</p>
     * @see  java.lang.Boolean
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default Boolean[] findLoadArray(F filter) throws RestException {
        return filter.toLoadArray();
    }

    /**
     * <code>queryLoadArray</code>
     * <p>The query load array method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return  {@link java.lang.Boolean} <p>The query load array return object is <code>Boolean</code> type.</p>
     * @see  java.lang.Boolean
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default Boolean[] queryLoadArray(F filter) throws RestException {
        return filter.toLoadArray();
    }

    /**
     * <code>fieldArray</code>
     * <p>The field array method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return  {@link java.lang.String} <p>The field array return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default String[] fieldArray(F filter) throws RestException {
        return filter.toFieldArray();
    }

    /**
     * <code>tablekey</code>
     * <p>The tablekey method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return K <p>The tablekey return object is <code>K</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    default K tablekey(F filter) throws RestException {
        return filter.toTablekey();
    }
}
