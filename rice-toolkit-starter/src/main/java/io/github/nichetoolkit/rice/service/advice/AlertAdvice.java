package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;

@SuppressWarnings("RedundantThrows")
public interface AlertAdvice<I> {

    default void beforeAlert(I id) throws RestException {}

    default void beforeAlertAll(Collection<I> idList) throws RestException {
        for (I id : idList) {
            beforeAlert(id);
        }
    }

    default void afterAlert(I id) throws RestException {}

    default void afterAlertAll(Collection<I> idList) throws RestException {
        for (I id : idList) {
            afterAlert(id);
        }
    }

}
