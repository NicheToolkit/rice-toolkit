package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.error.service.ServiceUnsupportedException;
import io.github.nichetoolkit.rice.filter.IdFilter;

/**
 * <p>ServiceHandler</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public interface ServiceAdvice<I, F extends IdFilter<I>> {

    default void doServiceHandle() throws RestException {}

    default void optionalFilter(F filter) throws RestException {}

    default String queryWhereSql(F filter) throws RestException {
        return filter.toSql();
    }

    default String deleteWhereSql(F filter) throws RestException {
        return filter.toIdSql().toDeleteSql();
    }

    default String removeWhereSql(F filter) throws RestException {
        return deleteWhereSql(filter);
    }

    default String operateWhereSql(F filter) throws RestException {
        return deleteWhereSql(filter);
    }

    default Boolean[] findLoadArray(F filter) throws RestException {
        return filter.toLoadArray();
    }

    default Boolean[] queryLoadArray(F filter) throws RestException {
        return filter.toLoadArray();
    }

    default String[] fieldArray(F filter) throws RestException {
        return filter.toFieldArray();
    }
}
