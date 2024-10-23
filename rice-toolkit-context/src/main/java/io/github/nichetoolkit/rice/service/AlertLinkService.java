package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;

import java.util.Collection;

/**
 * <code>AlertLinkService</code>
 * <p>The alert link service interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.service.AlertService
 * @since Jdk1.8
 */
public interface AlertLinkService<I, K> extends AlertService<I, K> {

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertAllByLinkIds(Collection<L> linkIdList, S status) throws RestException;

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tablekey   K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertAllByLinkIds(K tablekey, Collection<L> linkIdList, S status) throws RestException;

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @param status S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertByLinkId(L linkId, S status) throws RestException;

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param status   S <p>The status parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertByLinkId(K tablekey, L linkId, S status) throws RestException;

}
