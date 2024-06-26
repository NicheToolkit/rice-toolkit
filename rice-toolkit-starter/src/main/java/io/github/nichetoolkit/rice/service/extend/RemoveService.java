package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <p>RemoveService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RemoveService<K,I> {
    /**
     * 通过id集合批量逻辑删除
     * @param idList 对象的id集合
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAll(Collection<I> idList) throws RestException;

    /**
     * 通过id集合批量逻辑删除
     * @param idList 对象的id集合
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeAll(K tablekey, Collection<I> idList) throws RestException;

    /**
     * 通过id单个逻辑删除
     * @param id 对象的id
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeById(I id) throws RestException;

    /**
     * 通过id单个逻辑删除
     * @param id 对象的id
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void removeById(K tablekey, I id) throws RestException;
}
