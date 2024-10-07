package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;

import java.util.Collection;

/**
 * <code>AlertAdvice</code>
 * <p>The type alert advice interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface AlertAdvice<I> {

    /**
     * <code>beforeAlert</code>
     * <p>The alert method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeAlert(I id) throws RestException {}

    /**
     * <code>beforeAlertAll</code>
     * <p>The alert all method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeAlertAll(Collection<I> idList) throws RestException {
        for (I id : idList) {
            beforeAlert(id);
        }
    }

    /**
     * <code>afterAlert</code>
     * <p>The alert method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterAlert(I id) throws RestException {}

    /**
     * <code>afterAlertAll</code>
     * <p>The alert all method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterAlertAll(Collection<I> idList) throws RestException {
        for (I id : idList) {
            afterAlert(id);
        }
    }

}
