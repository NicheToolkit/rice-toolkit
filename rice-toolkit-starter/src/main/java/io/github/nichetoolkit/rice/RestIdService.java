package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.service.SuperService;
import io.github.nichetoolkit.rice.service.advice.BuilderAdvice;

public abstract class RestIdService<K, I, M extends BaseIdModel<M, E, I>, E extends BaseIdEntity<E, M, I>, F extends BaseFilter<I, K>> extends SuperService<K, I, M, E, F> implements BuilderAdvice<I, M, E> {

    @Override
    protected E createEntity(M model) throws RestException {
        return model.toEntity();
    }

    @Override
    protected M createModel(E entity) throws RestException {
        return entity.toModel();
    }

}
