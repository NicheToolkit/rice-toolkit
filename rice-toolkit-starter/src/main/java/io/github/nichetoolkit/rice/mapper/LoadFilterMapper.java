package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>LoadFilterMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface LoadFilterMapper<E extends IdEntity<I>, I> {

    /**
     * 通过filter查询条件查询
     * @param whereSql   过滤条件
     * @param loadParams 查询加载参数
     * @return List<T>
     */
    List<E> findAllByLoadWhere(@Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams);

    /**
     * 通过filter查询条件查询
     * @param whereSql   过滤条件
     * @param loadParams 查询加载参数
     * @return List<T>
     */
    List<E> findDynamicAllByLoadWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams);

}
