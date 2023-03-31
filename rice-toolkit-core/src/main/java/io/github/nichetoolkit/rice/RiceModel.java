package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;

/**
 * <p>RiceModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceModel<I,E extends IdEntity<I>> {

    default E toEntity() throws RestException {
        throw new UnsupportedErrorException("the method has not implemented, 'toEntity()' ");
    }

}
