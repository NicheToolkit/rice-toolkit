package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;

/**
 * <p>DeleteAdvice</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public interface DeleteAdvice<I,E extends IdEntity<I>> {

    default void beforeDelete(E entity) throws RestException {}

    default void beforeDeleteAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            beforeDelete(entity);
        }
    }

    default void afterDelete(E entity) throws RestException {}

    default void afterDeleteAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterDelete(entity);
        }
    }

}
