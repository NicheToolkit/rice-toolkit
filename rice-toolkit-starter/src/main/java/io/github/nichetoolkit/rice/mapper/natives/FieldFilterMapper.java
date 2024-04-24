package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>FieldFilterMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface FieldFilterMapper<E extends IdEntity<I>, I> {

    /**
     * 通过filter查询条件查询
     * @param whereSql    过滤条件
     * @param fieldParams 查询加载参数
     * @return List<T>
     */
    List<E> findAllByFieldWhere(@Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams);

    /**
     * 通过filter查询条件查询
     * @param whereSql    过滤条件
     * @param fieldParams 查询加载参数
     * @return List<T>
     */
    List<E> findDynamicAllByFieldWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams);

}
