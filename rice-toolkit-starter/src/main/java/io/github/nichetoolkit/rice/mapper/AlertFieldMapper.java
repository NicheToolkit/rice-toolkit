package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <p>OperateMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface AlertFieldMapper<I> {

    /**
     * 实体单个删除
     * @param id    实体id
     * @param field 实体属性
     * @param key   实体操作类型
     * @return Integer SQL影响行数（成功为1）
     */
    Integer alertById(@Param("id") I id, @Param("field") String field, @Param("key") Integer key);

    /**
     * 实体单个删除
     * @param id    实体id
     * @param field 实体属性
     * @param key   实体操作类型
     * @return Integer SQL影响行数（成功为1）
     */
    Integer alertById(@Param("tablename") String tablename, @Param("id") I id, @Param("field") String field, @Param("key") Integer key);

    /**
     * 实体批量删除
     * @param idList 实体id集合
     * @param field  实体属性
     * @param key    实体操作类型
     * @return Integer SQL影响行数
     */
    Integer alertAll(@Param("idList") Collection<I> idList, @Param("field") String field, @Param("key") Integer key);

    /**
     * 实体批量删除
     * @param idList 实体id集合
     * @param field  实体属性
     * @param key    实体操作类型
     * @return Integer SQL影响行数
     */
    Integer alertAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("field") String field, @Param("key") Integer key);


}
