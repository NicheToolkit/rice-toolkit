package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;

/**
 * <code>OperateAdvice</code>
 * <p>The type operate advice interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface OperateAdvice<E extends RestId<I>, I> {

    /**
     * <code>beforeOperate</code>
     * <p>the operate method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeOperate(E entity) throws RestException {
    }

    /**
     * <code>beforeOperateAll</code>
     * <p>the operate all method.</p>
     * @param entityList {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeOperateAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            beforeOperate(entity);
        }
    }

    /**
     * <code>afterOperate</code>
     * <p>the operate method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterOperate(E entity) throws RestException {
    }

    /**
     * <code>afterOperateAll</code>
     * <p>the operate all method.</p>
     * @param entityList {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterOperateAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterOperate(entity);
        }
    }

}
