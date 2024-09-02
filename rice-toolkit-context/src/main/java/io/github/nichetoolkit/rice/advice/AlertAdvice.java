package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;

import java.util.Collection;

/**
 * <code>AlertAdvice</code>
 * <p>The type alert advice interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface AlertAdvice<I> {

    /**
     * <code>beforeAlert</code>
     * <p>the alert method.</p>
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeAlert(I id) throws RestException {}

    /**
     * <code>beforeAlertAll</code>
     * <p>the alert all method.</p>
     * @param idList {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
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
     * <p>the alert method.</p>
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterAlert(I id) throws RestException {}

    /**
     * <code>afterAlertAll</code>
     * <p>the alert all method.</p>
     * @param idList {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterAlertAll(Collection<I> idList) throws RestException {
        for (I id : idList) {
            afterAlert(id);
        }
    }

}
