package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <code>RemoveLinkService</code>
 * <p>The type remove link service interface.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RemoveLinkService<K,I> {

    /**
     * <code>removeAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAllByLinkIds(Collection<I> linkIdList) throws RestException;

    /**
     * <code>removeAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param tablekey   K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAllByLinkIds(K tablekey, Collection<I> linkIdList) throws RestException;

    /**
     * <code>removeByLinkId</code>
     * <p>the by link id method.</p>
     * @param linkId I <p>the link id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeByLinkId(I linkId) throws RestException;

    /**
     * <code>removeByLinkId</code>
     * <p>the by link id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param linkId   I <p>the link id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeByLinkId(K tablekey, I linkId) throws RestException;

}
