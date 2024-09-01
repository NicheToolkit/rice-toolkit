package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;

@SuppressWarnings("RedundantThrows")
public interface SaveAdvice<I,M extends IdModel<I>> {

    default void beforeCreate(M model) throws RestException {
        beforeSave(model);
    }

    default void beforeUpdate(M model) throws RestException {
        beforeSave(model);
    }

    default void beforeSave(M model) throws RestException {}

    default void beforeSaveAll(Collection<M> modelList) throws RestException {
        for (M model : modelList) {
            beforeSave(model);
        }
    }

    default void afterCreate(M model) throws RestException {
        afterSave(model);
    }

    default void afterUpdate(M model) throws RestException {
        afterSave(model);
    }

    default void afterSave(M model) throws RestException {}

    default void afterSaveAll(Collection<M> modelList) throws RestException {
        for (M model : modelList) {
            afterSave(model);
        }
    }

}
