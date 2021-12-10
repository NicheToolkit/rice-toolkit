package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <p>ModelCheckService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public interface OptionalService<I,M extends IdModel<I>> extends QueryService<I,M> {

    default void optional(@NonNull IdModel<I> model) throws RestException {}

    default boolean existById(I id) throws RestException {
        return Optional.ofNullable(this.queryById(id)).isPresent();
    }
}
