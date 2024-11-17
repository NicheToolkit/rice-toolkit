package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;

import java.util.Collection;

/**
 * <code>RemoveAdvice</code>
 * <p>The remove advice interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface RemoveAdvice<E extends RestId<I>, I> {

    /**
     * <code>beforeRemove</code>
     * <p>The before remove method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeRemove(E entity) throws RestException {
    }

    /**
     * <code>beforeRemoveAll</code>
     * <p>The before remove all method.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeRemoveAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            beforeRemove(entity);
        }
    }

    /**
     * <code>afterRemove</code>
     * <p>The after remove method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterRemove(E entity) throws RestException {
    }

    /**
     * <code>afterRemoveAll</code>
     * <p>The after remove all method.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterRemoveAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterRemove(entity);
        }
    }

}
