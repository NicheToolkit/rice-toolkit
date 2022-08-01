package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <p>OperateMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface OperateMapper<I> {
    /**
     * 实体单个删除
     * @param id 实体id集合
     * @param operate 实体操作类型
     * @return Integer SQL影响行数（成功为1）
     */
    Integer operateById(@Param("id") I id, @Param("operate") Integer operate);

    /**
     * 实体批量删除
     * @param idList 实体id集合
     * @param operate 实体操作类型
     * @return Integer SQL影响行数
     */
    Integer operateAll(@Param("idList") Collection<I> idList, @Param("operate") Integer operate);

    /**
     * 通过filter查询条件 操作
     * @param whereSql 过滤条件
     * @param operate 实体操作类型
     * @return Integer SQL影响行数
     */
    Integer operateAllByWhere(@Param("whereSql") String whereSql, @Param("operate") Integer operate);
}
