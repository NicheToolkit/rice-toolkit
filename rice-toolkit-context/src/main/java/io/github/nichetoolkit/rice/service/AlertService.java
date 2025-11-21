package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rice.RestTableKey;

import java.util.Collection;

/**
 * <code>AlertService</code>
 * <p>The alert service interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AlertService<I, K> {

    /**
     * <code>alertAll</code>
     * <p>The alert all method.</p>
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
     * <p>The alert all method.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The tableKey parameter is <code>RestTableKey</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param status   S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertAll(RestTableKey<K> tableKey, Collection<I> idList, S status) throws RestException;

    /**
     * <code>alertById</code>
     * <p>The alert by id method.</p>
     * @param <S>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id     I <p>The id parameter is <code>I</code> type.</p>
     * @param status S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertById(I id, S status) throws RestException;

    /**
     * <code>alertById</code>
     * <p>The alert by id method.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The tableKey parameter is <code>RestTableKey</code> type.</p>
     * @param id       I <p>The id parameter is <code>I</code> type.</p>
     * @param status   S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertById(RestTableKey<K> tableKey, I id, S status) throws RestException;

    /**
     * <code>alertAll</code>
     * <p>The alert all method.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param idList     {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertAll(Collection<I> idList, S status, RestKey<String> statusName) throws RestException;

    /**
     * <code>alertAll</code>
     * <p>The alert all method.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The tableKey parameter is <code>RestTableKey</code> type.</p>
     * @param idList     {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertAll(RestTableKey<K> tableKey, Collection<I> idList, S status, RestKey<String> statusName) throws RestException;

    /**
     * <code>alertById</code>
     * <p>The alert by id method.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id         I <p>The id parameter is <code>I</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertById(I id, S status, RestKey<String> statusName) throws RestException;

    /**
     * <code>alertById</code>
     * <p>The alert by id method.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The tableKey parameter is <code>RestTableKey</code> type.</p>
     * @param id         I <p>The id parameter is <code>I</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <S> void alertById(RestTableKey<K> tableKey, I id, S status, RestKey<String> statusName) throws RestException;
}
