package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.error.ServiceUnsupportedException;
import io.github.nichetoolkit.rice.filter.IdFilter;

/**
 * <p>ServiceHandler</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public interface ServiceAdvice<I, F extends IdFilter<I>> {

    default void doServiceHandle() throws RestException {}

    default String queryWhereSql(F filter) throws RestException {
        return filter.toSql();
    }

    default String deleteWhereSql(F filter) throws RestException {
        throw new ServiceUnsupportedException();
    }

    default Boolean[] loadArray(F filter) throws RestException {
        return filter.toLoadArray();
    }

}
