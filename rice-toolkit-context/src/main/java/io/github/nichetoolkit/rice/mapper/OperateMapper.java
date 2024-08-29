package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface OperateMapper<I> {
    Integer operateById(@Param("id") I id, @Param("operate") Integer operate);

    Integer operateDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("operate") Integer operate);

    Integer operateAll(@Param("idList") Collection<I> idList, @Param("operate") Integer operate);

    Integer operateDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("operate") Integer operate);

    Integer operateAllByWhere(@Param("whereSql") String whereSql, @Param("operate") Integer operate);

    Integer operateDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("operate") Integer operate);
}
