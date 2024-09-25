package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rice.RestId;

import java.util.List;

@SuppressWarnings("RedundantThrows")
public interface MutateAdvice<M extends RestId<I>, E extends RestId<I>, I> {

    M mutateEntity(E entity, Boolean... isLoadArray) throws RestException;

    List<M> mutateEntityList(List<E> entityList, ConsumerActuator<E> actuator, Boolean... isLoadArray) throws RestException;

    E mutateModel(M model, Object... idArray) throws RestException;

    List<E> mutateModelList(List<M> modelList, ConsumerActuator<M> actuator, Object... idArray) throws RestException;

}
