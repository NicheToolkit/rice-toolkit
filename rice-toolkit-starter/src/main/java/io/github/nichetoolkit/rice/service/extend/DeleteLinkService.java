package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

public interface DeleteLinkService<I> {

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteAllByLinkIds(Collection<I> linkIdList) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteAllByLinkIds(String tablekey, Collection<I> linkIdList) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteByLinkId(I linkId) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteByLinkId(String tablekey, I linkId) throws RestException;

}
