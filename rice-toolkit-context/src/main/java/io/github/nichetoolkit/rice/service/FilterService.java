package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestPage;
import io.github.nichetoolkit.rice.filter.IdFilter;

/**
 * <code>FilterService</code>
 * <p>The filter service interface.</p>
 * @param <M>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <F>  {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @see  io.github.nichetoolkit.rice.filter.IdFilter
 * @see  io.github.nichetoolkit.rice.service.SaveService
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface FilterService<M extends RestId<I>, F extends IdFilter<I,K>,I,K> extends SaveService<M,I,K> {
    /**
     * <code>queryAllWithFilter</code>
     * <p>The query all with filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.RestPage} <p>The query all with filter return object is <code>RestPage</code> type.</p>
     * @see  io.github.nichetoolkit.rice.RestPage
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    RestPage<M> queryAllWithFilter(F filter) throws RestException;

    /**
     * <code>deleteAllWithFilter</code>
     * <p>The delete all with filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    void deleteAllWithFilter(F filter) throws RestException;

    /**
     * <code>removeAllWithFilter</code>
     * <p>The remove all with filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    void removeAllWithFilter(F filter) throws RestException;

    /**
     * <code>operateAllWithFilter</code>
     * <p>The operate all with filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    void operateAllWithFilter(F filter) throws RestException;

    /**
     * <code>alertAllWithFilter</code>
     * <p>The alert all with filter method.</p>
     * @param <S>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    <S> void alertAllWithFilter(F filter) throws RestException;

}
