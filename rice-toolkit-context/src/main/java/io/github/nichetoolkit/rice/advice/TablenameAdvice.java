package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;

import java.util.Collection;

/**
 * <code>TablenameAdvice</code>
 * <p>The tablename advice interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface TablenameAdvice<M extends RestId<I>, I, K> {

    /**
     * <code>resolveTablename</code>
     * <p>The resolve tablename method.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @return {@link java.lang.String} <p>The resolve tablename return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    String resolveTablename(K tablekey) throws RestException;

    /**
     * <code>resolveTablename</code>
     * <p>The resolve tablename method.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @return {@link java.lang.String} <p>The resolve tablename return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    String resolveTablename(K tablekey, M model) throws RestException;

    /**
     * <code>resolveTablename</code>
     * <p>The resolve tablename method.</p>
     * @param tablekey  K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.String} <p>The resolve tablename return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    String resolveTablename(K tablekey, Collection<M> modelList) throws RestException;
}
