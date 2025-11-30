package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.mybatis.load.RestLoad;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.RestTableKey;

import java.util.List;

/**
 * <code>NameService</code>
 * <p>The name service interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.IdModel} <p>The generic parameter is <code>IdModel</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @since Jdk17
 */
public interface NameService<M extends IdModel<I>, I, K> {

    /**
     * <code>queryByName</code>
     * <p>The query by name method.</p>
     * @param name        {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query by name return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    List<M> queryByName(String name, RestLoad... isLoadArray) throws RestException;

    /**
     * <code>queryByName</code>
     * <p>The query by name method.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param name        {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query by name return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    List<M> queryByName(RestTableKey<K> tableKey, String name, RestLoad... isLoadArray) throws RestException;

}
