package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;
import java.util.List;

/**
 * <p>BuilderAdvice</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public interface BuilderAdvice <I,M extends IdModel<I>, E extends IdEntity<I>> {

    @SuppressWarnings(value = "unchecked")
    default void buildEntity(M model, E entity, I... idArray) throws RestException {}

    @SuppressWarnings(value = "unchecked")
    default void buildEntityList(Collection<M> modelList, List<E> entityList, I... idArray) throws RestException {}

    default void buildModel(E entity, M model, Boolean... isLoadArray) throws RestException {}

    default void buildModelList(Collection<E> entityList, List<M> modelList, Boolean... isLoadArray) throws RestException {}

}
