package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;

import java.util.List;

/**
 * <code>NameService</code>
 * <p>The type name service interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.IdModel} <p>The generic parameter is <code>IdModel</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @since Jdk1.8
 */
public interface NameService<M extends IdModel<I>, I, K> {

    /**
     * <code>queryByName</code>
     * <p>The by name method.</p>
     * @param name        {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>The by name return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Boolean
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    List<M> queryByName(String name, Boolean... isLoadArray) throws RestException;

    /**
     * <code>queryByName</code>
     * <p>The by name method.</p>
     * @param tablekey    K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param name        {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>The by name return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Boolean
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    List<M> queryByName(K tablekey, String name, Boolean... isLoadArray) throws RestException;

}
