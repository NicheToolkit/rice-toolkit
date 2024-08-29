package io.github.nichetoolkit.rice.mapper.natives;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface AlertBiFieldMapper<I> {

    Integer alertBiFieldById(@Param("id") I id, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key);

    Integer alertDynamicBiFieldById(@Param("tablename") String tablename, @Param("id") I id, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key);

    Integer alertBiFieldAll(@Param("idList") Collection<I> idList, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key);

    Integer alertDynamicBiFieldAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key);

}
