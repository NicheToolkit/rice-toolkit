package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <p>OperateLinkMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface OperateLinkMapper<I> extends OperateMapper<I> {
    /**
     * 实体单个删除
     * @param linkId  实体id集合
     * @param operate 实体操作类型
     * @return Integer SQL影响行数（成功为1）
     */
    Integer operateByLinkId(@Param("id") I linkId, @Param("operate") Integer operate);

    /**
     * 实体单个删除
     * @param linkId  实体id集合
     * @param operate 实体操作类型
     * @return Integer SQL影响行数（成功为1）
     */
    Integer operateDynamicByLinkId(@Param("tablename") String tablename, @Param("id") I linkId, @Param("operate") Integer operate);

    /**
     * 实体批量删除
     * @param linkIdList 实体id集合
     * @param operate    实体操作类型
     * @return Integer SQL影响行数
     */
    Integer operateAllByLinkIds(@Param("linkIdList") Collection<I> linkIdList, @Param("operate") Integer operate);

    /**
     * 实体批量删除
     * @param linkIdList 实体id集合
     * @param operate    实体操作类型
     * @return Integer SQL影响行数
     */
    Integer operateDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<I> linkIdList, @Param("operate") Integer operate);

}
