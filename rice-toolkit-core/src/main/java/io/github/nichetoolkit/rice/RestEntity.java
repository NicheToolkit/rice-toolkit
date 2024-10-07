package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;

/**
 * <code>RestEntity</code>
 * <p>The type rest entity interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.IdModel} <p>The generic parameter is <code>IdModel</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @since Jdk1.8
 */
public interface RestEntity<I,M extends IdModel<I>> {

    /**
     * <code>toModel</code>
     * <p>The model method.</p>
     * @return M <p>The model return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default M toModel() throws RestException {
        throw new UnsupportedErrorException("the method has not implemented, 'toModel()' ");
    }

}
