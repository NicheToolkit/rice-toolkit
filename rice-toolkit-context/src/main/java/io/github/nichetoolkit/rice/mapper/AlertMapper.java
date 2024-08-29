package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface AlertMapper<I> {

    Integer alertById(@Param("id") I id, @Param("key") Integer key);

    Integer alertDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("key") Integer key);

    Integer alertAll(@Param("idList") Collection<I> idList, @Param("key") Integer key);

    Integer alertDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("key") Integer key);

}
