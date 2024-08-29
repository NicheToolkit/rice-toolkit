package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

public interface RemoveService<K,I> {
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAll(Collection<I> idList) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAll(K tablekey, Collection<I> idList) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeById(I id) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeById(K tablekey, I id) throws RestException;
}
