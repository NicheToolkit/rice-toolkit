package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;
import java.util.List;

/**
 * <code>QueryService</code>
 * <p>The type query service interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.IdModel} <p>the generic parameter is <code>IdModel</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @see io.github.nichetoolkit.rice.service.DeleteService
 * @since Jdk1.8
 */
public interface QueryService<M extends IdModel<I>, I, K> extends DeleteService<I, K> {

    /**
     * <code>queryAll</code>
     * <p>the all method.</p>
     * @param idList      {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Boolean
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    List<M> queryAll(Collection<I> idList, Boolean... isLoadArray) throws RestException;

    /**
     * <code>queryAll</code>
     * <p>the all method.</p>
     * @param tablekey    K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param idList      {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Boolean
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    List<M> queryAll(K tablekey, Collection<I> idList, Boolean... isLoadArray) throws RestException;

    /**
     * <code>queryById</code>
     * <p>the by id method.</p>
     * @param id          I <p>the id parameter is <code>I</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @return M <p>the by id return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    M queryById(I id, Boolean... isLoadArray) throws RestException;

    /**
     * <code>queryById</code>
     * <p>the by id method.</p>
     * @param tablekey    K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param id          I <p>the id parameter is <code>I</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @return M <p>the by id return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    M queryById(K tablekey, I id, Boolean... isLoadArray) throws RestException;

}
