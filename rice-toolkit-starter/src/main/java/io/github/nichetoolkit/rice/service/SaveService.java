package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * <p>SaveService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface SaveService<K, I, M extends IdModel<I>> extends QueryService<K,I, M> {

    /**
     * 单个保存
     * @param model 对象信息
     * @return M 创建的对象
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M save(M model, Object... idArray) throws RestException;

    /**
     * 单个保存
     * @param model 对象信息
     * @return M 创建的对象
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M save(K tablekey, M model, Object... idArray) throws RestException;

    /**
     * 批量保存（存在更新,不存在新增）
     * @param modelList 对象信息集合
     * @return List<M> 更新的对象
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    List<M> saveAll(Collection<M> modelList, Object... idArray) throws RestException;

    /**
     * 批量保存（存在更新,不存在新增）
     * @param modelList 对象信息集合
     * @return List<M> 更新的对象
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    List<M> saveAll(K tablekey, Collection<M> modelList, Object... idArray) throws RestException;

}
