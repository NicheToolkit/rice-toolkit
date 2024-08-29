package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.service.InfoService;
import io.github.nichetoolkit.rice.service.advice.BuilderAdvice;

public abstract class RestInfoService<K, I, M extends BaseInfoModel<M, E, I>, E extends BaseInfoEntity<E, M, I>, F extends BaseFilter<I, K>> extends InfoService<K, I, M, E, F> implements BuilderAdvice<I, M, E> {

    @Override
    protected E createEntity(M model) throws RestException {
        return model.toEntity();
    }

    @Override
    protected M createModel(E entity) throws RestException {
        return entity.toModel();
    }
}
