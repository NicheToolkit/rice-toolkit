package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;

/**
 * <p>AlertAdvice</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public interface AlertAdvice<I> {

    default void afterAlert(I id) throws RestException {}

    default void afterAlertAll(Collection<I> idList) throws RestException {
        for (I id : idList) {
            afterAlert(id);
        }
    }

}
