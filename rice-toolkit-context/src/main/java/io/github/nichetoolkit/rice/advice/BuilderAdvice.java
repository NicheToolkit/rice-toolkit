package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;
import java.util.List;

/**
 * <code>BuilderAdvice</code>
 * <p>The type builder advice interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.IdModel} <p>the generic parameter is <code>IdModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface BuilderAdvice<M extends RestId<I>, E extends RestId<I>, I> {

    /**
     * <code>buildEntity</code>
     * <p>the entity method.</p>
     * @param model   M <p>the model parameter is <code>M</code> type.</p>
     * @param entity  E <p>the entity parameter is <code>E</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void buildEntity(M model, E entity, Object... idArray) throws RestException {
    }

    /**
     * <code>buildEntityList</code>
     * <p>the entity list method.</p>
     * @param modelList  {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param entityList {@link java.util.List} <p>the entity list parameter is <code>List</code> type.</p>
     * @param idArray    {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.util.List
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void buildEntityList(Collection<M> modelList, List<E> entityList, Object... idArray) throws RestException {
    }

    /**
     * <code>buildModel</code>
     * <p>the model method.</p>
     * @param entity      E <p>the entity parameter is <code>E</code> type.</p>
     * @param model       M <p>the model parameter is <code>M</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void buildModel(E entity, M model, Boolean... isLoadArray) throws RestException {
    }

    /**
     * <code>buildModelList</code>
     * <p>the model list method.</p>
     * @param entityList  {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList   {@link java.util.List} <p>the model list parameter is <code>List</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.util.List
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void buildModelList(Collection<E> entityList, List<M> modelList, Boolean... isLoadArray) throws RestException {
    }

}
