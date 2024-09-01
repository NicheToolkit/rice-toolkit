package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.filter.IdFilter;
import org.springframework.lang.NonNull;

import java.util.Optional;

@SuppressWarnings("RedundantThrows")
public interface OptionalService<K, I, M extends IdModel<I>, F extends IdFilter<I, K>> extends QueryService<K, I, M> {

    default void optional(@NonNull M model) throws RestException {
    }

    default boolean existById(I id) throws RestException {
        return Optional.ofNullable(this.queryById(id)).isPresent();
    }

    default boolean existById(K tablekey, I id) throws RestException {
        return Optional.ofNullable(this.queryById(tablekey, id)).isPresent();
    }

    default void optionalQueryFilter(F filter) throws RestException {

    }

    default void optionalDeleteFilter(F filter) throws RestException {

    }

    default void optionalRemoveFilter(F filter) throws RestException {
        optionalDeleteFilter(filter);
    }

    default void optionalOperateFilter(F filter) throws RestException {
        optionalDeleteFilter(filter);
    }
}
