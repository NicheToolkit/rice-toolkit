package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import java.util.Collection;

/**
 * <code>DeleteLinkService</code>
 * <p>The delete link service interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.service.DeleteService
 * @since Jdk1.8
 */
public interface DeleteLinkService<I,K> extends DeleteService<I,K> {

    /**
     * <code>deleteAllByLinkIds</code>
     * <p>The delete all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> void deleteAllByLinkIds(Collection<L> linkIdList) throws RestException;

    /**
     * <code>deleteAllByLinkIds</code>
     * <p>The delete all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tablekey   K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> void deleteAllByLinkIds(K tablekey, Collection<L> linkIdList) throws RestException;

    /**
     * <code>deleteByLinkId</code>
     * <p>The delete by link id method.</p>
     * @param <L>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> void deleteByLinkId(L linkId) throws RestException;

    /**
     * <code>deleteByLinkId</code>
     * <p>The delete by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> void deleteByLinkId(K tablekey, L linkId) throws RestException;

}
