package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.service.InfoService;
import io.github.nichetoolkit.rice.advice.BuilderAdvice;

/**
 * <code>DefaultInfoService</code>
 * <p>The type default info service class.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.DefaultInfoModel} <p>the generic parameter is <code>DefaultInfoModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultInfoEntity} <p>the generic parameter is <code>DefaultInfoEntity</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.DefaultFilter} <p>the generic parameter is <code>DefaultFilter</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultInfoModel
 * @see io.github.nichetoolkit.rice.DefaultInfoEntity
 * @see io.github.nichetoolkit.rice.DefaultFilter
 * @see io.github.nichetoolkit.rice.service.InfoService
 * @see io.github.nichetoolkit.rice.advice.BuilderAdvice
 * @since Jdk1.8
 */
public abstract class DefaultInfoService<K, I, M extends DefaultInfoModel<M, E, I>, E extends DefaultInfoEntity<E, M, I>, F extends DefaultFilter<I, K>> extends InfoService<K, I, M, E, F> implements BuilderAdvice<I, M, E> {

    @Override
    protected E createEntity(M model) throws RestException {
        return model.toEntity();
    }

    @Override
    protected M createModel(E entity) throws RestException {
        return entity.toModel();
    }
}
