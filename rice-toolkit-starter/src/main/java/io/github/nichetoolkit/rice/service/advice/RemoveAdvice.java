package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;

/**
 * <p>RemoveAdvice</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public interface RemoveAdvice<I> {

    default void afterRemove(I id) throws RestException {}

    default void afterRemoveAll(Collection<I> idList) throws RestException {
        for (I id : idList) {
            afterRemove(id);
        }
    }

}
