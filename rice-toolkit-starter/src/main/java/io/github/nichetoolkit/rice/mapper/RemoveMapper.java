package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <p>RemoveMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RemoveMapper<I> {

    /**
     * 实体单个删除
     * @param id 实体id集合
     * @return Integer SQL影响行数（成功为1）
     */
    Integer removeById(@Param("id") I id, @Param("sign") String sign);

    /**
     * 实体单个删除
     * @param id 实体id集合
     * @return Integer SQL影响行数（成功为1）
     */
    Integer removeDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("sign") String sign);

    /**
     * 实体批量删除
     * @param idList 实体id集合
     * @return Integer SQL影响行数
     */
    Integer removeAll(@Param("idList") Collection<I> idList, @Param("sign") String sign);

    /**
     * 实体批量删除
     * @param idList 实体id集合
     * @return Integer SQL影响行数
     */
    Integer removeDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("sign") String sign);

    /**
     * 通过filter查询条件删除
     * @param whereSql 过滤条件
     * @return Integer SQL影响行数
     */
    Integer removeAllByWhere(@Param("whereSql") String whereSql, @Param("sign") String sign);

    /**
     * 通过filter查询条件删除
     * @param whereSql 过滤条件
     * @return Integer SQL影响行数
     */
    Integer removeDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql,@Param("sign") String sign);
}
