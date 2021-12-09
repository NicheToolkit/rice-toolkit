package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.service.BuilderAdvice;
import io.github.nichetoolkit.rice.service.InfoService;

/**
 * <p>RestService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceInfoService<M extends RiceInfoModel<M,E>,E extends RiceInfoEntity<E,M>,F extends RiceFilter> extends InfoService<String, M, E, F> implements BuilderAdvice<String,M, E> {

    @Override
    protected E createEntity(M model) throws RestException {
        return model.toEntity();
    }

    @Override
    protected M createModel(E entity) throws RestException {
        return entity.toModel();
    }
}
