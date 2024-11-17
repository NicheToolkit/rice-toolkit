package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;

/**
 * <code>DeleteAdvice</code>
 * <p>The delete advice interface.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface DeleteAdvice<E extends RestId<I>, I> {

    /**
     * <code>beforeDelete</code>
     * <p>The before delete method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    default void beforeDelete(E entity) throws RestException {
    }

    /**
     * <code>beforeDeleteAll</code>
     * <p>The before delete all method.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default void beforeDeleteAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            beforeDelete(entity);
        }
    }

    /**
     * <code>afterDelete</code>
     * <p>The after delete method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    default void afterDelete(E entity) throws RestException {
    }

    /**
     * <code>afterDeleteAll</code>
     * <p>The after delete all method.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default void afterDeleteAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterDelete(entity);
        }
    }

}
