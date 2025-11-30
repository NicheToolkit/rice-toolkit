package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestTableKey;
import io.github.nichetoolkit.rice.filter.IdFilter;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

/**
 * <code>OptionalService</code>
 * <p>The optional service interface.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see io.github.nichetoolkit.rice.service.QueryService
 * @see java.lang.SuppressWarnings
 * @since Jdk17
 */
@SuppressWarnings("RedundantThrows")
public interface OptionalService<M extends RestId<I>, F extends IdFilter<I, K>, I, K> extends QueryService<M, I, K> {

    /**
     * <code>optional</code>
     * <p>The optional method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optional(@NonNull M model) throws RestException {
    }

    /**
     * <code>existById</code>
     * <p>The exist by id method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @return boolean <p>The exist by id return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default boolean existById(I id) throws RestException {
        return Optional.ofNullable(this.queryById(id)).isPresent();
    }

    /**
     * <code>existById</code>
     * <p>The exist by id method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param id       I <p>The id parameter is <code>I</code> type.</p>
     * @return boolean <p>The exist by id return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    default boolean existById(RestTableKey<K> tableKey, I id) throws RestException {
        return Optional.ofNullable(this.queryById(tableKey, id)).isPresent();
    }

    /**
     * <code>optionalQueryFilter</code>
     * <p>The optional query filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optionalQueryFilter(F filter) throws RestException {
    }

    /**
     * <code>optionalDeleteFilter</code>
     * <p>The optional delete filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optionalDeleteFilter(F filter) throws RestException {
    }

    /**
     * <code>optionalRemoveFilter</code>
     * <p>The optional remove filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optionalRemoveFilter(F filter) throws RestException {
        optionalDeleteFilter(filter);
    }

    /**
     * <code>optionalOperateFilter</code>
     * <p>The optional operate filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optionalOperateFilter(F filter) throws RestException {
        optionalDeleteFilter(filter);
    }

    /**
     * <code>optionalAlertFilter</code>
     * <p>The optional alert filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void optionalAlertFilter(F filter) throws RestException {
        optionalDeleteFilter(filter);
    }
}
