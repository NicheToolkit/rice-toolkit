package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.RestPage;
import io.github.nichetoolkit.rice.filter.IdFilter;

public interface FilterService<K,I,M extends IdModel<I>, F extends IdFilter<I,K>> extends SaveService<K,I,M> {
    RestPage<M> queryAllWithFilter(F filter) throws RestException;

    void deleteAllWithFilter(F filter) throws RestException;

    void removeAllWithFilter(F filter) throws RestException;

    void operateAllWithFilter(F filter) throws RestException;

}
