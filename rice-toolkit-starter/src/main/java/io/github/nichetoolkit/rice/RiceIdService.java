package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.service.advice.BuilderAdvice;
import io.github.nichetoolkit.rice.service.SuperService;

import java.util.Date;

/**
 * <p>RiceIdService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceIdService<M extends RiceIdModel<M, E>, E extends RiceIdEntity<E, M>, F extends RiceFilter> extends RestIdService<Date, String, String, M, E, F> {

    @Override
    protected E createEntity(M model) throws RestException {
        return model.toEntity();
    }

    @Override
    protected M createModel(E entity) throws RestException {
        return entity.toModel();
    }

}
