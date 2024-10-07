package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <code>AlertService</code>
 * <p>The type alert service interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AlertService<I> {

    /**
     * <code>alertAll</code>
     * <p>The all method.</p>
     * @param idList  {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param keyType {@link io.github.nichetoolkit.rest.RestKey} <p>The key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertAll(Collection<I> idList, RestKey<Integer> keyType) throws RestException;

    /**
     * <code>alertAll</code>
     * <p>The all method.</p>
     * @param tablekey {@link java.lang.String} <p>The tablekey parameter is <code>String</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param keyType  {@link io.github.nichetoolkit.rest.RestKey} <p>The key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertAll(String tablekey, Collection<I> idList, RestKey<Integer> keyType) throws RestException;

    /**
     * <code>alertById</code>
     * <p>The by id method.</p>
     * @param id      I <p>The id parameter is <code>I</code> type.</p>
     * @param keyType {@link io.github.nichetoolkit.rest.RestKey} <p>The key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertById(I id, RestKey<Integer> keyType) throws RestException;

    /**
     * <code>alertById</code>
     * <p>The by id method.</p>
     * @param tablekey {@link java.lang.String} <p>The tablekey parameter is <code>String</code> type.</p>
     * @param id       I <p>The id parameter is <code>I</code> type.</p>
     * @param keyType  {@link io.github.nichetoolkit.rest.RestKey} <p>The key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertById(String tablekey, I id, RestKey<Integer> keyType) throws RestException;
}
