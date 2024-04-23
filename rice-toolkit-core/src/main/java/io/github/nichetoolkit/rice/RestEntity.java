package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;

/**
 * <p>RiceEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestEntity<I,M extends IdModel<I>> {

    default M toModel() throws RestException {
        throw new UnsupportedErrorException("the method has not implemented, 'toModel()' ");
    }

}
