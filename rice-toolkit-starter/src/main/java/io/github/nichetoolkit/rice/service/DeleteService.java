package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import org.checkerframework.checker.units.qual.K;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <p>DeleteService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface DeleteService<K,I>{
    /**
     * 通过id集合批量删除
     * @param idList 对象的id集合
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteAll(Collection<I> idList) throws RestException;

    /**
     * 通过id集合批量删除
     * @param idList 对象的id集合
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteAll(K tablekey, Collection<I> idList) throws RestException;

    /**
     * 通过id单个删除
     * @param id 对象的id
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteById(I id) throws RestException;

    /**
     * 通过id单个删除
     * @param id 对象的id
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void deleteById(K tablekey, I id) throws RestException;
}
