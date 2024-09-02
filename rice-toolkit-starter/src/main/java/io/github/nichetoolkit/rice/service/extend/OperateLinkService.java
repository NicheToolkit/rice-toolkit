package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <code>OperateLinkService</code>
 * <p>The type operate link service interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface OperateLinkService<I> {

    /**
     * <code>operateAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param linkIdList  {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateAllByLinkIds(Collection<I> linkIdList, OperateType operateType) throws RestException;

    /**
     * <code>operateAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param tablekey    {@link java.lang.String} <p>the tablekey parameter is <code>String</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateAllByLinkIds(String tablekey, Collection<I> linkIdList, OperateType operateType) throws RestException;

    /**
     * <code>operateByLinkId</code>
     * <p>the by link id method.</p>
     * @param linkId      I <p>the link id parameter is <code>I</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateByLinkId(I linkId, OperateType operateType) throws RestException;

    /**
     * <code>operateByLinkId</code>
     * <p>the by link id method.</p>
     * @param tablekey    {@link java.lang.String} <p>the tablekey parameter is <code>String</code> type.</p>
     * @param linkId      I <p>the link id parameter is <code>I</code> type.</p>
     * @param operateType {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate type parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateByLinkId(String tablekey, I linkId, OperateType operateType) throws RestException;

}
