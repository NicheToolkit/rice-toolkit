package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;

/**
 * <code>TablenameAdvice</code>
 * <p>The type tablename advice interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface TablenameAdvice<M extends RestId<I>, I, K> {

    /**
     * <code>resolveTablename</code>
     * <p>the tablename method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @return {@link java.lang.String} <p>the tablename return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    String resolveTablename(K tablekey) throws RestException;

    /**
     * <code>resolveTablename</code>
     * <p>the tablename method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @return {@link java.lang.String} <p>the tablename return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    String resolveTablename(K tablekey, M model) throws RestException;

    /**
     * <code>resolveTablename</code>
     * <p>the tablename method.</p>
     * @param tablekey  K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.String} <p>the tablename return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    String resolveTablename(K tablekey, Collection<M> modelList) throws RestException;
}
