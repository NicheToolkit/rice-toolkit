package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;

/**
 * <p>DeleteAdvice</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public interface DeleteAdvice<I> {

    default void afterDelete(I id) throws RestException {}

    default void afterDeleteAll(Collection<I> idList) throws RestException {
        for (I id : idList) {
            afterDelete(id);
        }
    }

}
