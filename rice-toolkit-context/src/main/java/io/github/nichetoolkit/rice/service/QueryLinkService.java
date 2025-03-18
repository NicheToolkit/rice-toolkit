package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;
import java.util.List;

public interface QueryLinkService<M extends RestId<I>, I, K> extends QueryService<M, I, K> {

    <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, Boolean... isLoadArray) throws RestException;

    <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, String[] fickleArray, Boolean... isLoadArray) throws RestException;

    <L> List<M> queryAllByLinkIds(K tablekey, Collection<L> linkIdList, Boolean... isLoadArray) throws RestException;

    <L> List<M> queryAllByLinkIds(K tablekey, Collection<L> linkIdList, String[] fickleArray, Boolean... isLoadArray) throws RestException;

    <L> M queryByLinkId(L linkId, Boolean... isLoadArray) throws RestException;

    <L> M queryByLinkId(L linkId, String[] fickleArray, Boolean... isLoadArray) throws RestException;

    <L> M queryByLinkId(K tablekey, L linkId, Boolean... isLoadArray) throws RestException;

    <L> M queryByLinkId(K tablekey, L linkId, String[] fickleArray, Boolean... isLoadArray) throws RestException;

}
