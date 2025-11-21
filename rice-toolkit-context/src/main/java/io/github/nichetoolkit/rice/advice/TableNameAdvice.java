package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestTableKey;

import java.util.Collection;

/**
 * <code>TableNameAdvice</code>
 * <p>The tableName advice interface.</p>
 * @param <M>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface TableNameAdvice<M extends RestId<I>, I, K> {

    /**
     * <code>resolveTableName</code>
     * <p>The resolve tableName method.</p>
     * @param tableKey K <p>The tableKey parameter is <code>K</code> type.</p>
     * @return  {@link java.lang.String} <p>The resolve tableName return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    String resolveTableName(RestTableKey<K> tableKey) throws RestException;

    /**
     * <code>resolveTableName</code>
     * <p>The resolve tableName method.</p>
     * @param tableKey K <p>The tableKey parameter is <code>K</code> type.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @return  {@link java.lang.String} <p>The resolve tableName return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    String resolveTableName(RestTableKey<K> tableKey, M model) throws RestException;

    /**
     * <code>resolveTableName</code>
     * <p>The resolve tableName method.</p>
     * @param tableKey K <p>The tableKey parameter is <code>K</code> type.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.String} <p>The resolve tableName return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    String resolveTableName(RestTableKey<K> tableKey, Collection<M> modelList) throws RestException;
}
