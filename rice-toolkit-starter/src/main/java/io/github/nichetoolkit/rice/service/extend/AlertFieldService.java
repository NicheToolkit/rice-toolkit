package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <code>AlertFieldService</code>
 * <p>The type alert field service interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AlertFieldService<I> {

    /**
     * <code>alertFieldAll</code>
     * <p>the field all method.</p>
     * @param idList  {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param keyType {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertFieldAll(Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException;

    /**
     * <code>alertFieldAll</code>
     * <p>the field all method.</p>
     * @param tablekey {@link java.lang.String} <p>the tablekey parameter is <code>String</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param keyType  {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertFieldAll(String tablekey, Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException;

    /**
     * <code>alertFieldById</code>
     * <p>the field by id method.</p>
     * @param id      I <p>the id parameter is <code>I</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param keyType {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertFieldById(I id, String field, RestKey<Integer> keyType) throws RestException;

    /**
     * <code>alertFieldById</code>
     * <p>the field by id method.</p>
     * @param tablekey {@link java.lang.String} <p>the tablekey parameter is <code>String</code> type.</p>
     * @param id       I <p>the id parameter is <code>I</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param keyType  {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertFieldById(String tablekey, I id, String field, RestKey<Integer> keyType) throws RestException;
}
