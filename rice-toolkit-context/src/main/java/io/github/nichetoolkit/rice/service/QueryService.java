package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;
import java.util.List;

public interface QueryService<M extends RestId<I>, I, K> extends DeleteService<I, K> {

    List<M> queryAll(Collection<I> idList, Boolean... isLoadArray) throws RestException;

    List<M> queryAll(K tablekey, Collection<I> idList, Boolean... isLoadArray) throws RestException;

    M queryById(I id, Boolean... isLoadArray) throws RestException;

    M queryById(I id, String[] fickleArray, Boolean... isLoadArray) throws RestException;

    M queryById(K tablekey, I id, Boolean... isLoadArray) throws RestException;

    M queryById(K tablekey, I id, String[] fickleArray, Boolean... isLoadArray) throws RestException;

}
