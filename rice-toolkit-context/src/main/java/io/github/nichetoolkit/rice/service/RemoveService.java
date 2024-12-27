package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import java.util.Collection;

/**
 * <code>RemoveService</code>
 * <p>The remove service interface.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RemoveService<I, K> {
    /**
     * <code>removeAll</code>
     * <p>The remove all method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    void removeAll(Collection<I> idList) throws RestException;

    /**
     * <code>removeAll</code>
     * <p>The remove all method.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    void removeAll(K tablekey, Collection<I> idList) throws RestException;

    /**
     * <code>removeById</code>
     * <p>The remove by id method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    void removeById(I id) throws RestException;

    /**
     * <code>removeById</code>
     * <p>The remove by id method.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    void removeById(K tablekey, I id) throws RestException;
}
