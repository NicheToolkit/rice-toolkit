package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FieldFilterMapper<E extends IdEntity<I>, I> {

    List<E> findAllByFieldWhere(@Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams);

    List<E> findDynamicAllByFieldWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams);

}
