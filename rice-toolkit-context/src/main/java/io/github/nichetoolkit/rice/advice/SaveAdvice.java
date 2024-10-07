package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;

/**
 * <code>SaveAdvice</code>
 * <p>The type save advice interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface SaveAdvice<M extends RestId<I>, I> {

    /**
     * <code>beforeCreate</code>
     * <p>The create method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeCreate(M model) throws RestException {
        beforeSave(model);
    }

    /**
     * <code>beforeUpdate</code>
     * <p>The update method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeUpdate(M model) throws RestException {
        beforeSave(model);
    }

    /**
     * <code>beforeSave</code>
     * <p>The save method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeSave(M model) throws RestException {
    }

    /**
     * <code>beforeSaveAll</code>
     * <p>The save all method.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeSaveAll(Collection<M> modelList) throws RestException {
        for (M model : modelList) {
            beforeSave(model);
        }
    }

    /**
     * <code>afterCreate</code>
     * <p>The create method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterCreate(M model) throws RestException {
        afterSave(model);
    }

    /**
     * <code>afterUpdate</code>
     * <p>The update method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterUpdate(M model) throws RestException {
        afterSave(model);
    }

    /**
     * <code>afterSave</code>
     * <p>The save method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterSave(M model) throws RestException {
    }

    /**
     * <code>afterSaveAll</code>
     * <p>The save all method.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterSaveAll(Collection<M> modelList) throws RestException {
        for (M model : modelList) {
            afterSave(model);
        }
    }

}
