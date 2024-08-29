package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

public interface OperateService<K,I> {
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateAll(Collection<I> idList, OperateType operateType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateAll(K tablekey, Collection<I> idList, OperateType operateType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateById(I id, OperateType operateType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateById(K tablekey, I id, OperateType operateType) throws RestException;

}
