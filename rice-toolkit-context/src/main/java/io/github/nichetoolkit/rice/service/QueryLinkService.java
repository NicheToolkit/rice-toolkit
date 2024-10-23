package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;
import java.util.List;

/**
 * <code>QueryLinkService</code>
 * <p>The query link service interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see io.github.nichetoolkit.rice.service.QueryService
 * @since Jdk1.8
 */
public interface QueryLinkService<M extends RestId<I>, I, K> extends QueryService<M, I, K> {

    /**
     * <code>queryAllByLinkIds</code>
     * <p>The query all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>The query all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Boolean
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, Boolean... isLoadArray) throws RestException;

    /**
     * <code>queryAllByLinkIds</code>
     * <p>The query all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tablekey    K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>The query all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Boolean
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> List<M> queryAllByLinkIds(K tablekey, Collection<L> linkIdList, Boolean... isLoadArray) throws RestException;

    /**
     * <code>queryByLinkId</code>
     * <p>The query by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @return M <p>The query by link id return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> M queryByLinkId(L linkId, Boolean... isLoadArray) throws RestException;

    /**
     * <code>queryByLinkId</code>
     * <p>The query by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tablekey    K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @return M <p>The query by link id return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L> M queryByLinkId(K tablekey, L linkId, Boolean... isLoadArray) throws RestException;

}
