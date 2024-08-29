package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

public interface AlertService<I> {

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertAll(Collection<I> idList, RestKey<Integer> keyType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertAll(String tablekey, Collection<I> idList, RestKey<Integer> keyType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertById(I id, RestKey<Integer> keyType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertById(String tablekey, I id, RestKey<Integer> keyType) throws RestException;
}
