package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;

/**
 * <code>OperateAdvice</code>
 * <p>The operate advice interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface OperateAdvice<E extends RestId<I>, I> {

    /**
     * <code>beforeOperate</code>
     * <p>The before operate method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeOperate(E entity) throws RestException {
    }

    /**
     * <code>beforeOperateAll</code>
     * <p>The before operate all method.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The after operate method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterOperate(E entity) throws RestException {
    }

    /**
     * <code>afterOperateAll</code>
     * <p>The after operate all method.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterOperateAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterOperate(entity);
        }
    }

}
