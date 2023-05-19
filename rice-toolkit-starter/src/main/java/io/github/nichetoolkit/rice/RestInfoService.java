package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.service.InfoService;
import io.github.nichetoolkit.rice.service.advice.BuilderAdvice;

/**
 * <p>RestInfoService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RestInfoService<K, I, M extends RestInfoModel<M, E, I>, E extends RestInfoEntity<E, M, I>, F extends RestFilter<I, K>> extends InfoService<K, I, M, E, F> implements BuilderAdvice<I, M, E> {

    @Override
    protected E createEntity(M model) throws RestException {
        return model.toEntity();
    }

    @Override
    protected M createModel(E entity) throws RestException {
        return entity.toModel();
    }
}
