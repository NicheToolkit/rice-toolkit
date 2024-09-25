package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.RestId;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * <code>SaveService</code>
 * <p>The type save service interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.IdModel} <p>the generic parameter is <code>IdModel</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @see io.github.nichetoolkit.rice.service.QueryService
 * @since Jdk1.8
 */
public interface SaveService<M extends RestId<I>, I, K> extends QueryService<M, I, K> {

    /**
     * <code>save</code>
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
    M save(M model, Object... idArray) throws RestException;

    /**
     * <code>save</code>
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
    M save(K tablekey, M model, Object... idArray) throws RestException;

    /**
     * <code>saveAll</code>
     * <p>the all method.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param idArray   {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return {@link java.util.List} <p>the all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Object
     * @see java.util.List
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    List<M> saveAll(Collection<M> modelList, Object... idArray) throws RestException;

    /**
     * <code>saveAll</code>
     * <p>the all method.</p>
     * @param tablekey  K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param idArray   {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return {@link java.util.List} <p>the all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Object
     * @see java.util.List
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    List<M> saveAll(K tablekey, Collection<M> modelList, Object... idArray) throws RestException;

}
