package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

public interface AlertBiFieldService<I> {

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertBiFieldAll(Collection<I> idList, String field, String biField, RestKey<Integer> keyType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertBiFieldAll(String tablekey, Collection<I> idList, String field, String biField, RestKey<Integer> keyType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertBiFieldById(I id, String field, String biField, RestKey<Integer> keyType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertBiFieldById(String tablekey, I id, String field, String biField, RestKey<Integer> keyType) throws RestException;
}
