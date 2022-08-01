package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>LoadMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface LoadMapper<E extends IdEntity<I>,I> {
    /**
     * 通过id查询实体
     * @param id 实体id
     * @param loadParams 查询加载参数
     * @return T 查询的数据
     */
    default E findByLoadId(@Param("id") I id, @Param("loadParams") Boolean... loadParams) {
        return null;
    }

    /**
     * 实体批量查询
     * @param idList 实体集合
     * @param loadParams 查询加载参数
     * @return List<T> 查询的数据集合
     */
    default List<E> findAllLoad(@Param("idList") Collection<I> idList, @Param("loadParams") Boolean... loadParams) {
        return Collections.emptyList();
    }

    /**
     * 通过filter查询条件查询
     * @param whereSql 过滤条件
     * @param loadParams 查询加载参数
     * @return List<T>
     */
    default List<E> findAllByLoadWhere(@Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams) {
        return Collections.emptyList();
    }
}
