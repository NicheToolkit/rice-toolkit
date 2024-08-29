package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

public interface OperateLinkService<I> {

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateAllByLinkIds(Collection<I> linkIdList, OperateType operateType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateAllByLinkIds(String tablekey, Collection<I> linkIdList, OperateType operateType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateByLinkId(I linkId, OperateType operateType) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateByLinkId(String tablekey, I linkId, OperateType operateType) throws RestException;

}
