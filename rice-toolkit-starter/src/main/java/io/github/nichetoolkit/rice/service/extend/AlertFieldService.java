package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

public interface AlertFieldService<I> {

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertFieldAll(Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertFieldAll(String tablekey, Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertFieldById(I id, String field, RestKey<Integer> keyType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertFieldById(String tablekey, I id, String field, RestKey<Integer> keyType) throws RestException;
}
