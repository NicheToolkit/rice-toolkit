package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface LoadMapper<E extends IdEntity<I>, I> {
    E findByLoadId(@Param("id") I id, @Param("loadParams") Boolean... loadParams);

    E findDynamicByLoadId(@Param("tablename") String tablename, @Param("id") I id, @Param("loadParams") Boolean... loadParams);

    List<E> findAllLoad(@Param("idList") Collection<I> idList, @Param("loadParams") Boolean... loadParams);

    List<E> findDynamicAllLoad(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("loadParams") Boolean... loadParams);

}
