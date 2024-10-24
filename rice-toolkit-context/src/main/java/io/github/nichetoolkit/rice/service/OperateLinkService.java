package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.service.OperateService;
import java.util.Collection;

/**
 * <code>OperateLinkService</code>
 * <p>The operate link service interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.service.OperateService
 * @since Jdk1.8
 */
public interface OperateLinkService<I,K> extends OperateService<I,K> {

    /**
     * <code>operateAllByLinkIds</code>
     * <p>The operate all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> void operateAllByLinkIds(Collection<L> linkIdList, OperateType operateType) throws RestException;

    /**
     * <code>operateAllByLinkIds</code>
     * <p>The operate all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tablekey    K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> void operateAllByLinkIds(K tablekey, Collection<L> linkIdList, OperateType operateType) throws RestException;

    /**
     * <code>operateByLinkId</code>
     * <p>The operate by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> void operateByLinkId(L linkId, OperateType operateType) throws RestException;

    /**
     * <code>operateByLinkId</code>
     * <p>The operate by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tablekey    K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> void operateByLinkId(K tablekey, L linkId, OperateType operateType) throws RestException;

}
