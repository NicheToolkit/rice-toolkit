package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.filter.IdFilter;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <code>OptionalService</code>
 * <p>The type optional service interface.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.IdModel} <p>the generic parameter is <code>IdModel</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>the generic parameter is <code>IdFilter</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see io.github.nichetoolkit.rice.service.QueryService
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public interface OptionalService<K, I, M extends IdModel<I>, F extends IdFilter<I, K>> extends QueryService<K, I, M> {

    /**
     * <code>optional</code>
     * <p>the method.</p>
     * @param model M <p>the model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optional(@NonNull M model) throws RestException {
    }

    /**
     * <code>existById</code>
     * <p>the by id method.</p>
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     * @return boolean <p>the by id return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default boolean existById(I id) throws RestException {
        return Optional.ofNullable(this.queryById(id)).isPresent();
    }

    /**
     * <code>existById</code>
     * <p>the by id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param id       I <p>the id parameter is <code>I</code> type.</p>
     * @return boolean <p>the by id return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default boolean existById(K tablekey, I id) throws RestException {
        return Optional.ofNullable(this.queryById(tablekey, id)).isPresent();
    }

    /**
     * <code>optionalQueryFilter</code>
     * <p>the query filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optionalQueryFilter(F filter) throws RestException {

    }

    /**
     * <code>optionalDeleteFilter</code>
     * <p>the delete filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optionalDeleteFilter(F filter) throws RestException {

    }

    /**
     * <code>optionalRemoveFilter</code>
     * <p>the remove filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optionalRemoveFilter(F filter) throws RestException {
        optionalDeleteFilter(filter);
    }

    /**
     * <code>optionalOperateFilter</code>
     * <p>the operate filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optionalOperateFilter(F filter) throws RestException {
        optionalDeleteFilter(filter);
    }
}
