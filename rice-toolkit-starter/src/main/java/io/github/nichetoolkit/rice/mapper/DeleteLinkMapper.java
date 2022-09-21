package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <p>DeleteLinkMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface DeleteLinkMapper<I> {

    /**
     * 实体单个删除
     * @param linkId 实体id集合
     * @return Integer SQL影响行数（成功为1）
     */
    Integer deleteByLinkId(@Param("linkId") I linkId);

    /**
     * 实体批量删除
     * @param linkIdList 实体id集合
     * @return Integer SQL影响行数
     */
    Integer deleteAllByLinkIds(@Param("linkIdList") Collection<I> linkIdList);

}
