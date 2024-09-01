package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdEntity;

import java.util.Collection;

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
