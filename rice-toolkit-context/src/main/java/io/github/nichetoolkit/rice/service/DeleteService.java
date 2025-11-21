package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestTableKey;

import java.util.Collection;

/**
 * <code>DeleteService</code>
 * <p>The delete service interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface DeleteService<I, K> {
    /**
     * <code>deleteAll</code>
     * <p>The delete all method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    void deleteAll(Collection<I> idList) throws RestException;

    /**
     * <code>deleteAll</code>
     * <p>The delete all method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The tableKey parameter is <code>RestTableKey</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    void deleteAll(RestTableKey<K> tableKey, Collection<I> idList) throws RestException;

    /**
     * <code>deleteById</code>
     * <p>The delete by id method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    void deleteById(I id) throws RestException;

    /**
     * <code>deleteById</code>
     * <p>The delete by id method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The tableKey parameter is <code>RestTableKey</code> type.</p>
     * @param id       I <p>The id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    void deleteById(RestTableKey<K> tableKey, I id) throws RestException;
}
