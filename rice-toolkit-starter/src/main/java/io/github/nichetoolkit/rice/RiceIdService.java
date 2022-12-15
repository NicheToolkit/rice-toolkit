package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.service.advice.BuilderAdvice;
import io.github.nichetoolkit.rice.service.SuperService;

/**
 * <p>RiceIdService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceIdService<M extends RiceIdModel<M,E>,E extends RiceIdEntity<E,M>,F extends RiceFilter> extends SuperService<String, String, M, E, F> implements BuilderAdvice<String,M, E> {

    @Override
    protected E createEntity(M model) throws RestException {
        return model.toEntity();
    }

    @Override
    protected M createModel(E entity) throws RestException {
        return entity.toModel();
    }

}
