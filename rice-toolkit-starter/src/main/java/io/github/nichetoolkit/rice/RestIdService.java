package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.service.SuperService;
import io.github.nichetoolkit.rice.service.advice.BuilderAdvice;

/**
 * <p>RestIdService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RestIdService<D, K, I, M extends RestIdModel<M, E, I>, E extends RestIdEntity<E, M, I>, F extends RestFilter<D, I, K>> extends SuperService<K, I, M, E, F> implements BuilderAdvice<I, M, E> {

    @Override
    protected E createEntity(M model) throws RestException {
        return model.toEntity();
    }

    @Override
    protected M createModel(E entity) throws RestException {
        return entity.toModel();
    }

}
