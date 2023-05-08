package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.filter.PageFilter;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>RemoveMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface FilterMapper<E> {

    /**
     * 通过filter查询条件 操作
     * @param whereSql 过滤条件
     * @param operate  实体操作类型
     * @return Integer SQL影响行数
     */
    Integer operateAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("operate") Integer operate);

    /**
     * 通过filter查询条件 操作
     * @param whereSql 过滤条件
     * @param operate  实体操作类型
     * @return Integer SQL影响行数
     */
    Integer operateDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("operate") Integer operate);

    /**
     * 通过filter查询条件删除
     * @param whereSql 过滤条件
     * @return Integer SQL影响行数
     */
    Integer removeAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("sign") String sign);

    /**
     * 通过filter查询条件删除
     * @param whereSql 过滤条件
     * @return Integer SQL影响行数
     */
    Integer removeDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("sign") String sign);

    /**
     * 通过filter查询条件查询
     * @param whereSql 过滤条件
     * @return List<T>
     */
    List<E> findAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter);

    /**
     * 通过filter查询条件查询
     * @param whereSql 过滤条件
     * @return List<T>
     */
    List<E> findDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter);

    /**
     * 通过filter查询条件删除
     * @param whereSql 过滤条件
     */
    Integer deleteAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter);

    /**
     * 通过filter查询条件删除
     * @param whereSql 过滤条件
     */
    Integer deleteDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter);
}
