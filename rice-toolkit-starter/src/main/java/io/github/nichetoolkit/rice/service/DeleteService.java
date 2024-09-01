package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

public interface DeleteService<K,I>{
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteAll(Collection<I> idList) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteAll(K tablekey, Collection<I> idList) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteById(I id) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteById(K tablekey, I id) throws RestException;
}
