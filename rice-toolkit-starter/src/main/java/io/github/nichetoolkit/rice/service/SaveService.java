package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface SaveService<K, I, M extends IdModel<I>> extends QueryService<K,I, M> {

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M save(M model, Object... idArray) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M save(K tablekey, M model, Object... idArray) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    List<M> saveAll(Collection<M> modelList, Object... idArray) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    List<M> saveAll(K tablekey, Collection<M> modelList, Object... idArray) throws RestException;

}
