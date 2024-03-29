package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;

/**
 * <p>RemoveAdvice</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public interface RemoveAdvice<I,E extends IdEntity<I>> {

    default void beforeRemove(E entity) throws RestException {}

    default void beforeRemoveAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            beforeRemove(entity);
        }
    }

    default void afterRemove(E entity) throws RestException {}

    default void afterRemoveAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterRemove(entity);
        }
    }

}
