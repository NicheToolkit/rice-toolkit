package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>FindMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface FieldMapper<E extends IdEntity<I>,I> {

    /**
     * 通过filter查询条件查询
     * @param whereSql 过滤条件
     * @param fieldParams 查询加载参数
     * @return List<T>
     */
    List<E> findAllByFieldWhere(@Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams);
}
