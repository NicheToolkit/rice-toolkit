package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.RestPage;
import io.github.nichetoolkit.rice.filter.IdFilter;

/**
 * <p>FilterService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface FilterService<I,M extends IdModel<I>, F extends IdFilter<I>> extends SaveService<I,M> {
    /**
     * 通过过滤器查询
     * @param filter 过滤器
     * @return GxPage<M> 查询的数据（分页）
     * @throws RestException 模块异常
     */
    RestPage<M> queryAllWithFilter(F filter) throws RestException;

    /**
     * 通过过滤器删除
     * @param filter 过滤器
     * @throws RestException 模块异常
     */
    void deleteAllWithFilter(F filter) throws RestException;

    /**
     * 通过过滤器删除
     * @param filter 过滤器
     * @throws RestException 模块异常
     */
    void removeAllWithFilter(F filter) throws RestException;

    /**
     * 通过过滤器操作
     * @param filter 过滤器
     * @throws RestException 模块异常
     */
    void operateAllWithFilter(F filter) throws RestException;

}
