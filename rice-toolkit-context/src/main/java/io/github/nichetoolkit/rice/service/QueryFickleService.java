package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.mybatis.fickle.RestFickle;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;
import java.util.List;

public interface QueryFickleService<M extends RestId<I>, I, K> extends DeleteService<I, K> {

    List<M> queryAll(Collection<I> idList, Collection<RestFickle<?>> fickleList, Boolean... isLoadArray) throws RestException;

    List<M> queryAll(K tablekey, Collection<I> idList, Collection<RestFickle<?>> fickleList, Boolean... isLoadArray) throws RestException;

    M queryById(I id, Collection<RestFickle<?>> fickleList, Boolean... isLoadArray) throws RestException;

    M queryById(K tablekey, I id, Collection<RestFickle<?>> fickleList, Boolean... isLoadArray) throws RestException;

}
