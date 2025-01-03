package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rice.RestId;

import java.util.List;

/**
 * <code>MutateAdvice</code>
 * <p>The mutate advice interface.</p>
 * @param <M>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface MutateAdvice<M extends RestId<I>, E extends RestId<I>, I> {

    /**
     * <code>mutateEntity</code>
     * <p>The mutate entity method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.Boolean
     * @see  io.github.nichetoolkit.rest.RestException
     * @return M <p>The mutate entity return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    M mutateEntity(E entity, Boolean... isLoadArray) throws RestException;

    /**
     * <code>mutateEntityList</code>
     * <p>The mutate entity list method.</p>
     * @param entityList {@link java.util.List} <p>The entity list parameter is <code>List</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see  java.lang.Boolean
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.List} <p>The mutate entity list return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    List<M> mutateEntityList(List<E> entityList, ConsumerActuator<E> actuator, Boolean... isLoadArray) throws RestException;

    /**
     * <code>mutateModel</code>
     * <p>The mutate model method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @return E <p>The mutate model return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    E mutateModel(M model, Object... idArray) throws RestException;

    /**
     * <code>mutateModelList</code>
     * <p>The mutate model list method.</p>
     * @param modelList {@link java.util.List} <p>The model list parameter is <code>List</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.List} <p>The mutate model list return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    List<E> mutateModelList(List<M> modelList, ConsumerActuator<M> actuator, Object... idArray) throws RestException;

}
