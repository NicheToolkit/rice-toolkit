package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;

public interface RestModel<I,E extends IdEntity<I>> {

    default E toEntity() throws RestException {
        throw new UnsupportedErrorException("the method has not implemented, 'toEntity()' ");
    }

}
