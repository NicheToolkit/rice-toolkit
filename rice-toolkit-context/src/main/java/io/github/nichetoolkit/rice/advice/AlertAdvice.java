package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;

@SuppressWarnings("RedundantThrows")
public interface AlertAdvice<E extends RestId<I>,I> {

    default void beforeAlert(E entity) throws RestException {}

    default void beforeAlertAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            beforeAlert(entity);
        }
    }

    default void afterAlert(E entity) throws RestException {}

    default void afterAlertAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterAlert(entity);
        }
    }

}
