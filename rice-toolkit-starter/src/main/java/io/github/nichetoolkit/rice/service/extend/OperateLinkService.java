package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <code>OperateLinkService</code>
 * <p>The type operate link service interface.</p>
 * @param <L> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface OperateLinkService<L,K> {

    /**
     * <code>operateAllByLinkIds</code>
     * <p>The all by link ids method.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateAllByLinkIds(Collection<L> linkIdList, OperateType operateType) throws RestException;

    /**
     * <code>operateAllByLinkIds</code>
     * <p>The all by link ids method.</p>
     * @param tablekey    K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateAllByLinkIds(K tablekey, Collection<L> linkIdList, OperateType operateType) throws RestException;

    /**
     * <code>operateByLinkId</code>
     * <p>The by link id method.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateByLinkId(L linkId, OperateType operateType) throws RestException;

    /**
     * <code>operateByLinkId</code>
     * <p>The by link id method.</p>
     * @param tablekey    K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateByLinkId(K tablekey, L linkId, OperateType operateType) throws RestException;

}
