package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;

@SuppressWarnings("RedundantThrows")
public interface OperateAdvice<I,E extends IdEntity<I>> {

    default void beforeOperate(E entity) throws RestException {}

    default void beforeOperateAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            beforeOperate(entity);
        }
    }

    default void afterOperate(E entity) throws RestException {}

    default void afterOperateAll(Collection<E> entityList) throws RestException {
        for (E entity : entityList) {
            afterOperate(entity);
        }
    }

}
