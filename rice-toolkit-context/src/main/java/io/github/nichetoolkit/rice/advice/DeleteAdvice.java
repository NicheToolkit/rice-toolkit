package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;

/**
 * <code>DeleteAdvice</code>
 * <p>The type delete advice interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface DeleteAdvice<E extends RestId<I>, I> {

    /**
     * <code>beforeDelete</code>
     * <p>the delete method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeDelete(E entity) throws RestException {
    }

    /**
     * <code>beforeDeleteAll</code>
     * <p>the delete all method.</p>
     * @param entityList {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeDeleteAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            beforeDelete(entity);
        }
    }

    /**
     * <code>afterDelete</code>
     * <p>the delete method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterDelete(E entity) throws RestException {
    }

    /**
     * <code>afterDeleteAll</code>
     * <p>the delete all method.</p>
     * @param entityList {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterDeleteAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterDelete(entity);
        }
    }

}
