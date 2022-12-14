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
@SuppressWarnings("RedundantThrows")
public interface SaveService<I, M extends IdModel<I>> extends QueryService<I, M> {

    /**
     * 单个保存
     * @param model 对象信息
     * @return M 创建的对象
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    @SuppressWarnings(value = "unchecked")
    M save(M model, I... idArray) throws RestException;

    /**
     * 单个保存
     * @param model 对象信息
     * @return M 创建的对象
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    @SuppressWarnings(value = "unchecked")
    M save(String tableKey, M model, I... idArray) throws RestException;

    /**
     * 批量保存（存在更新,不存在新增）
     * @param modelList 对象信息集合
     * @return List<M> 更新的对象
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    @SuppressWarnings(value = "unchecked")
    List<M> saveAll(Collection<M> modelList, I... idArray) throws RestException;

    /**
     * 批量保存（存在更新,不存在新增）
     * @param modelList 对象信息集合
     * @return List<M> 更新的对象
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    @SuppressWarnings(value = "unchecked")
    List<M> saveAll(String tableKey, Collection<M> modelList, I... idArray) throws RestException;

}
