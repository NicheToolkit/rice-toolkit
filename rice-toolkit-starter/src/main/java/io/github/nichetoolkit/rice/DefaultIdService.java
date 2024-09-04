package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.service.SuperService;
import io.github.nichetoolkit.rice.advice.BuilderAdvice;

/**
 * <code>DefaultIdService</code>
 * <p>The type default id service class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.DefaultIdModel} <p>the generic parameter is <code>DefaultIdModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultIdEntity} <p>the generic parameter is <code>DefaultIdEntity</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.DefaultFilter} <p>the generic parameter is <code>DefaultFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultIdModel
 * @see io.github.nichetoolkit.rice.DefaultIdEntity
 * @see io.github.nichetoolkit.rice.DefaultFilter
 * @see io.github.nichetoolkit.rice.service.SuperService
 * @see io.github.nichetoolkit.rice.advice.BuilderAdvice
 * @since Jdk1.8
 */
public abstract class DefaultIdService<M extends DefaultIdModel<M, E, I>, E extends DefaultIdEntity<E, M, I>, F extends DefaultFilter<I, K>, I, K> extends SuperService<M, E, F, I, K> implements BuilderAdvice<M, E, I> {

    @Override
    protected E createEntity(M model) throws RestException {
        return model.toEntity();
    }

    @Override
    protected M createModel(E entity) throws RestException {
        return entity.toModel();
    }

}
