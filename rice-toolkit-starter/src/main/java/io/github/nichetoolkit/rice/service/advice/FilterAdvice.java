package io.github.nichetoolkit.rice.service.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.error.service.ServiceUnsupportedException;
import io.github.nichetoolkit.rice.filter.IdFilter;
import org.checkerframework.checker.units.qual.K;

@SuppressWarnings("RedundantThrows")
public interface FilterAdvice<K, I, F extends IdFilter<I, K>> {

    default void doServiceHandle() throws RestException {
    }

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

    default K tablekey(F filter) throws RestException {
        return filter.toTablekey();
    }
}
