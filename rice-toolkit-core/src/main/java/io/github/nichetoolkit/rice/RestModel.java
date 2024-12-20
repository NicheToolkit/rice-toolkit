package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;

/**
 * <code>RestModel</code>
 * <p>The rest model interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>The generic parameter is <code>IdEntity</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @since Jdk1.8
 */
public interface RestModel<I,E extends RestId<I>> {

    /**
     * <code>toEntity</code>
     * <p>The to entity method.</p>
     * @return E <p>The to entity return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default E toEntity() throws RestException {
        throw new UnsupportedErrorException("the method has not implemented, 'toEntity()' ");
    }

}
