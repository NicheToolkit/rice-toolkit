package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rice.RestTableKey;

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
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertAllByLinkIds(Collection<L> linkIdList,  S state) throws RestException;

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, S state) throws RestException;

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, S state) throws RestException;

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, RestKey<String> linkName, S state) throws RestException;

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @param state  S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertByLinkId(L linkId,  S state) throws RestException;

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param state    S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertByLinkId(RestTableKey<K> tableKey, L linkId,  S state) throws RestException;

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state    S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertByLinkId(L linkId, RestKey<String> linkName, S state) throws RestException;

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state    S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertByLinkId(RestTableKey<K> tableKey, L linkId, RestKey<String> linkName, S state) throws RestException;

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName  {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertAllByLinkIds(Collection<L> linkIdList,  S state, RestKey<String> stateName) throws RestException;

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName  {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, S state, RestKey<String> stateName) throws RestException;

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName  {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, S state, RestKey<String> stateName) throws RestException;

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName  {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, RestKey<String> linkName, S state, RestKey<String> stateName) throws RestException;

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertByLinkId(L linkId,  S state, RestKey<String> stateName) throws RestException;

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey  {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertByLinkId(RestTableKey<K> tableKey, L linkId,  S state, RestKey<String> stateName) throws RestException;

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName  {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertByLinkId(L linkId, RestKey<String> linkName, S state, RestKey<String> stateName) throws RestException;

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey  {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName  {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    <L, S> void alertByLinkId(RestTableKey<K> tableKey, L linkId, RestKey<String> linkName, S state, RestKey<String> stateName) throws RestException;

}
