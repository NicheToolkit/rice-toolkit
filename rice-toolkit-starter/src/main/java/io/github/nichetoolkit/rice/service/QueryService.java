package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;

import java.util.Collection;
import java.util.List;

public interface QueryService<K,I, M extends IdModel<I>> extends DeleteService<K,I> {

    List<M> queryAll(Collection<I> idList, Boolean... isLoadArray) throws RestException;

    List<M> queryAll(K tablekey, Collection<I> idList, Boolean... isLoadArray) throws RestException;

    M queryById(I id, Boolean... isLoadArray) throws RestException;

    M queryById(K tablekey, I id, Boolean... isLoadArray) throws RestException;

}
