package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <code>RemoveService</code>
 * <p>The type remove service interface.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RemoveService<K,I> {
    /**
     * <code>removeAll</code>
     * <p>the all method.</p>
     * @param idList {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAll(Collection<I> idList) throws RestException;

    /**
     * <code>removeAll</code>
     * <p>the all method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAll(K tablekey, Collection<I> idList) throws RestException;

    /**
     * <code>removeById</code>
     * <p>the by id method.</p>
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeById(I id) throws RestException;

    /**
     * <code>removeById</code>
     * <p>the by id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param id       I <p>the id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeById(K tablekey, I id) throws RestException;
}
