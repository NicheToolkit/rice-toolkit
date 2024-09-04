package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdEntity;

import java.util.Collection;

/**
 * <code>RemoveAdvice</code>
 * <p>The type remove advice interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface RemoveAdvice<E extends IdEntity<I>, I> {

    /**
     * <code>beforeRemove</code>
     * <p>the remove method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeRemove(E entity) throws RestException {
    }

    /**
     * <code>beforeRemoveAll</code>
     * <p>the remove all method.</p>
     * @param entityList {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
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
     * <p>the remove method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterRemove(E entity) throws RestException {
    }

    /**
     * <code>afterRemoveAll</code>
     * <p>the remove all method.</p>
     * @param entityList {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterRemoveAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterRemove(entity);
        }
    }

}
