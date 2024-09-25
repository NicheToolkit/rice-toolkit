package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestId;

import java.util.Collection;

@SuppressWarnings("RedundantThrows")
public interface TablenameAdvice<M extends RestId<I>,I, K> {

    String resolveTablename(K tablekey) throws RestException;

    String resolveTablename(K tablekey, M model) throws RestException;

    String resolveTablename(K tablekey, Collection<M> modelList) throws RestException;
}
