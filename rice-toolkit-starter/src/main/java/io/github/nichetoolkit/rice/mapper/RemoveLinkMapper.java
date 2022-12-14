package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <p>RemoveLinkMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RemoveLinkMapper<I> extends RemoveMapper<I> {

    /**
     * 实体单个删除
     * @param linkId 实体id集合
     * @return Integer SQL影响行数（成功为1）
     */
    Integer removeByLinkId(@Param("linkId") I linkId, @Param("sign") String sign);

    /**
     * 实体单个删除
     * @param linkId 实体id集合
     * @return Integer SQL影响行数（成功为1）
     */
    Integer removeByLinkId(@Param("tablename") String tablename, @Param("linkId") I linkId, @Param("sign") String sign);

    /**
     * 实体批量删除
     * @param linkIdList 实体id集合
     * @return Integer SQL影响行数
     */
    Integer removeAllByLinkIds(@Param("linkIdList") Collection<I> linkIdList, @Param("sign") String sign);

    /**
     * 实体批量删除
     * @param linkIdList 实体id集合
     * @return Integer SQL影响行数
     */
    Integer removeAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<I> linkIdList, @Param("sign") String sign);

}
