package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rice.RestId;

import java.util.List;

/**
 * <code>MutateAdvice</code>
 * <p>The type mutate advice interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface MutateAdvice<M extends RestId<I>, E extends RestId<I>, I> {

    /**
     * <code>mutateEntity</code>
     * <p>the entity method.</p>
     * @param entity      E <p>the entity parameter is <code>E</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @return M <p>the entity return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    M mutateEntity(E entity, Boolean... isLoadArray) throws RestException;

    /**
     * <code>mutateEntityList</code>
     * <p>the entity list method.</p>
     * @param entityList  {@link java.util.List} <p>the entity list parameter is <code>List</code> type.</p>
     * @param actuator    {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the entity list return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    List<M> mutateEntityList(List<E> entityList, ConsumerActuator<E> actuator, Boolean... isLoadArray) throws RestException;

    /**
     * <code>mutateModel</code>
     * <p>the model method.</p>
     * @param model   M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return E <p>the model return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    E mutateModel(M model, Object... idArray) throws RestException;

    /**
     * <code>mutateModelList</code>
     * <p>the model list method.</p>
     * @param modelList {@link java.util.List} <p>the model list parameter is <code>List</code> type.</p>
     * @param actuator  {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @param idArray   {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return {@link java.util.List} <p>the model list return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    List<E> mutateModelList(List<M> modelList, ConsumerActuator<M> actuator, Object... idArray) throws RestException;

}
