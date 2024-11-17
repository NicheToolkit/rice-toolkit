package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;
import java.util.List;

/**
 * <code>BuilderAdvice</code>
 * <p>The builder advice interface.</p>
 * @param <M>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface BuilderAdvice<M extends RestId<I>, E extends RestId<I>, I> {

    /**
     * <code>buildEntity</code>
     * <p>The build entity method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default void buildEntity(M model, E entity, Object... idArray) throws RestException {
    }

    /**
     * <code>buildEntityList</code>
     * <p>The build entity list method.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @param entityList {@link java.util.List} <p>The entity list parameter is <code>List</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @see  java.util.Collection
     * @see  java.util.List
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default void buildEntityList(Collection<M> modelList, List<E> entityList, Object... idArray) throws RestException {
    }

    /**
     * <code>buildModel</code>
     * <p>The build model method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.Boolean
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default void buildModel(E entity, M model, Boolean... isLoadArray) throws RestException {
    }

    /**
     * <code>buildModelList</code>
     * <p>The build model list method.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @param modelList {@link java.util.List} <p>The model list parameter is <code>List</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @see  java.util.Collection
     * @see  java.util.List
     * @see  java.lang.Boolean
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default void buildModelList(Collection<E> entityList, List<M> modelList, Boolean... isLoadArray) throws RestException {
    }

}
