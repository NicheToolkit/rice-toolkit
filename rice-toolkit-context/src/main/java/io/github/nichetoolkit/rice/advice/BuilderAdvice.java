package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("RedundantThrows")
public interface BuilderAdvice <I,M extends IdModel<I>, E extends IdEntity<I>> {

    default void buildEntity(M model, E entity, Object... idArray) throws RestException {}

    default void buildEntityList(Collection<M> modelList, List<E> entityList, Object... idArray) throws RestException {}

    default void buildModel(E entity, M model, Boolean... isLoadArray) throws RestException {}

    default void buildModelList(Collection<E> entityList, List<M> modelList, Boolean... isLoadArray) throws RestException {}

}
