package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * <p>SingleService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface SingleService<K, I, M extends IdModel<I>> {
    /**
     * 单个创建
     * @param model 对象信息
     * @return M 创建的对象
     * @throws RestException 模块异常
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M create(M model, I... idArray) throws RestException;

    /**
     * 单个创建
     * @param model 对象信息
     * @return M 创建的对象
     * @throws RestException 模块异常
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M create(K tablekey, M model, I... idArray) throws RestException;

    /**
     * 单个更新
     * @param model 对象信息
     * @return M 创建的对象
     * @throws RestException 模块异常
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M update(M model, I... idArray) throws RestException;

    /**
     * 单个更新
     * @param model 对象信息
     * @return M 创建的对象
     * @throws RestException 模块异常
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    M update(K tablekey, M model, I... idArray) throws RestException;
}
