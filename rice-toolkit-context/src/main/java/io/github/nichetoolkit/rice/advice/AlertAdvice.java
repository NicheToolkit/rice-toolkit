package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;

/**
 * <code>AlertAdvice</code>
 * <p>The alert advice interface.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface AlertAdvice<E extends RestId<I>,I> {

    /**
     * <code>beforeAlert</code>
     * <p>The before alert method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    default void beforeAlert(E entity) throws RestException {}

    /**
     * <code>beforeAlertAll</code>
     * <p>The before alert all method.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default void beforeAlertAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            beforeAlert(entity);
        }
    }

    /**
     * <code>afterAlert</code>
     * <p>The after alert method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    default void afterAlert(E entity) throws RestException {}

    /**
     * <code>afterAlertAll</code>
     * <p>The after alert all method.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default void afterAlertAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterAlert(entity);
        }
    }

}
