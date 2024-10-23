package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;

import java.util.Collection;

/**
 * <code>AlertService</code>
 * <p>The type alert service interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AlertService<I, K> {

    /**
     * <code>alertAll</code>
     * <p>The all method.</p>
     * @param <S>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param status S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertAll(Collection<I> idList, S status) throws RestException;

    /**
     * <code>alertAll</code>
     * <p>The all method.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tablekey {@link java.lang.String} <p>The tablekey parameter is <code>String</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param status   S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertAll(K tablekey, Collection<I> idList, S status) throws RestException;

    /**
     * <code>alertById</code>
     * <p>The by id method.</p>
     * @param <S>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id     I <p>The id parameter is <code>I</code> type.</p>
     * @param status S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertById(I id, S status) throws RestException;

    /**
     * <code>alertById</code>
     * <p>The by id method.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tablekey {@link java.lang.String} <p>The tablekey parameter is <code>String</code> type.</p>
     * @param id       I <p>The id parameter is <code>I</code> type.</p>
     * @param status   S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertById(K tablekey, I id, S status) throws RestException;
}
