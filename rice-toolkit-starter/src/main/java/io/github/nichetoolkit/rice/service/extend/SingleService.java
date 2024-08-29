package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public interface SingleService<K, I, M extends IdModel<I>> {
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M create(M model, Object... idArray) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M create(K tablekey, M model, Object... idArray) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M update(M model, Object... idArray) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M update(K tablekey, M model, Object... idArray) throws RestException;
}
