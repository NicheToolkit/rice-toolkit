package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoadFilterMapper<E extends IdEntity<I>, I> {

    List<E> findAllByLoadWhere(@Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams);

    List<E> findDynamicAllByLoadWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams);

}
