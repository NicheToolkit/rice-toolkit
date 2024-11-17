package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;
import java.util.List;

/**
 * <code>SaveService</code>
 * <p>The save service interface.</p>
 * @param <M>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @see  io.github.nichetoolkit.rice.service.QueryService
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface SaveService<M extends RestId<I>, I, K> extends QueryService<M, I, K> {

    /**
     * <code>save</code>
     * <p>The save method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @return M <p>The save return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    M save(M model, Object... idArray) throws RestException;

    /**
     * <code>save</code>
     * <p>The save method.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @return M <p>The save return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    M save(K tablekey, M model, Object... idArray) throws RestException;

    /**
     * <code>saveAll</code>
     * <p>The save all method.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.Object
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.List} <p>The save all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    List<M> saveAll(Collection<M> modelList, Object... idArray) throws RestException;

    /**
     * <code>saveAll</code>
     * <p>The save all method.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.Object
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.List} <p>The save all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    List<M> saveAll(K tablekey, Collection<M> modelList, Object... idArray) throws RestException;

}
