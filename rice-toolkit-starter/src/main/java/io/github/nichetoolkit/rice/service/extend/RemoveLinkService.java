package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <p>RemoveLinkService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RemoveLinkService<K,I> {

    /**
     * 通过id集合批量逻辑删除
     * @param linkIdList 对象的id集合
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAllByLinkIds(Collection<I> linkIdList) throws RestException;

    /**
     * 通过id集合批量逻辑删除
     * @param linkIdList 对象的id集合
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAllByLinkIds(K tablekey, Collection<I> linkIdList) throws RestException;

    /**
     * 通过id单个逻辑删除
     * @param linkId 对象的id
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeByLinkId(I linkId) throws RestException;

    /**
     * 通过id单个逻辑删除
     * @param linkId 对象的id
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeByLinkId(K tablekey, I linkId) throws RestException;

}
