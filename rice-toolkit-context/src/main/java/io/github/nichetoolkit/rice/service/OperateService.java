package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.enums.OperateType;
import java.util.Collection;

/**
 * <code>OperateService</code>
 * <p>The operate service interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface OperateService<I, K> {
    /**
     * <code>operateAll</code>
     * <p>The operate all method.</p>
     * @param idList      {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see io.github.nichetoolkit.rest.RestException
     */
    void operateAll(Collection<I> idList, OperateType operateType) throws RestException;

    /**
     * <code>operateAll</code>
     * <p>The operate all method.</p>
     * @param tablekey    K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param idList      {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see io.github.nichetoolkit.rest.RestException
     */
    void operateAll(K tablekey, Collection<I> idList, OperateType operateType) throws RestException;

    /**
     * <code>operateById</code>
     * <p>The operate by id method.</p>
     * @param id          I <p>The id parameter is <code>I</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see io.github.nichetoolkit.rest.RestException
     */
    void operateById(I id, OperateType operateType) throws RestException;

    /**
     * <code>operateById</code>
     * <p>The operate by id method.</p>
     * @param tablekey    K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param id          I <p>The id parameter is <code>I</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see io.github.nichetoolkit.rest.RestException
     */
    void operateById(K tablekey, I id, OperateType operateType) throws RestException;

}
