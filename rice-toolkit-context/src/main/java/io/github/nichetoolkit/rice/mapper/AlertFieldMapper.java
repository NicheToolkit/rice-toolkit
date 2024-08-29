package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface AlertFieldMapper<I> {

    Integer alertFieldById(@Param("id") I id, @Param("field") String field, @Param("key") Integer key);

    Integer alertDynamicFieldById(@Param("tablename") String tablename, @Param("id") I id, @Param("field") String field, @Param("key") Integer key);

    Integer alertFieldAll(@Param("idList") Collection<I> idList, @Param("field") String field, @Param("key") Integer key);

    Integer alertDynamicFieldAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("field") String field, @Param("key") Integer key);


}
