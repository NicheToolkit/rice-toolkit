package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

public interface RemoveLinkService<K,I> {

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAllByLinkIds(Collection<I> linkIdList) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAllByLinkIds(K tablekey, Collection<I> linkIdList) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeByLinkId(I linkId) throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeByLinkId(K tablekey, I linkId) throws RestException;

}
