package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.filter.IdFilter;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <p>ModelCheckService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public interface OptionalService<I, M extends IdModel<I>, F extends IdFilter<I>> extends QueryService<I, M> {

    default void optional(@NonNull M model) throws RestException {
    }

    default boolean existById(I id) throws RestException {
        return Optional.ofNullable(this.queryById(id)).isPresent();
    }

    default boolean existById(String tableKey, I id) throws RestException {
        return Optional.ofNullable(this.queryById(tableKey, id)).isPresent();
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
