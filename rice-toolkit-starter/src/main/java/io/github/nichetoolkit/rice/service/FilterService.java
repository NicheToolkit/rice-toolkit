package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestPage;
import io.github.nichetoolkit.rice.filter.IdFilter;

/**
 * <code>FilterService</code>
 * <p>The type filter service interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.IdModel} <p>the generic parameter is <code>IdModel</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>the generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see io.github.nichetoolkit.rice.service.SaveService
 * @since Jdk1.8
 */
public interface FilterService<M extends RestId<I>, F extends IdFilter<I,K>,I,K> extends SaveService<M,I,K> {
    /**
     * <code>queryAllWithFilter</code>
     * <p>the all with filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>the all with filter return object is <code>RestPage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestPage
     * @see io.github.nichetoolkit.rest.RestException
     */
    RestPage<M> queryAllWithFilter(F filter) throws RestException;

    /**
     * <code>deleteAllWithFilter</code>
     * <p>the all with filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    void deleteAllWithFilter(F filter) throws RestException;

    /**
     * <code>removeAllWithFilter</code>
     * <p>the all with filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    void removeAllWithFilter(F filter) throws RestException;

    /**
     * <code>operateAllWithFilter</code>
     * <p>the all with filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    void operateAllWithFilter(F filter) throws RestException;

}
