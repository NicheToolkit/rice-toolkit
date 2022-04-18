package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;

/**
 * <p>BuilderAdvice</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public interface SaveAdvice<I,M extends IdModel<I>> {

    default void afterCreate(M model) throws RestException {
        afterSave(model);
    }

    default void afterUppdate(M model) throws RestException {
        afterSave(model);
    }

    default void afterSave(M model) throws RestException {}

    default void afterSaveAll(Collection<M> modelList) throws RestException {
        for (M m : modelList) {
            afterSave(m);
        }
    }

}
