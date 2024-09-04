package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * <code>SingleService</code>
 * <p>The type single service interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.IdModel} <p>the generic parameter is <code>IdModel</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @since Jdk1.8
 */
public interface SingleService<M extends IdModel<I>, I, K> {
    /**
     * <code>create</code>
     * <p>the method.</p>
     * @param model   M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return M <p>the return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M create(M model, Object... idArray) throws RestException;

    /**
     * <code>create</code>
     * <p>the method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray  {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return M <p>the return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M create(K tablekey, M model, Object... idArray) throws RestException;

    /**
     * <code>update</code>
     * <p>the method.</p>
     * @param model   M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return M <p>the return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M update(M model, Object... idArray) throws RestException;

    /**
     * <code>update</code>
     * <p>the method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray  {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return M <p>the return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M update(K tablekey, M model, Object... idArray) throws RestException;
}
