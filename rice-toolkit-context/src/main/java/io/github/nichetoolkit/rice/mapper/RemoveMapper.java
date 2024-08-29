package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface RemoveMapper<I> {

    Integer removeById(@Param("id") I id, @Param("sign") String sign);

    Integer removeDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("sign") String sign);

    Integer removeAll(@Param("idList") Collection<I> idList, @Param("sign") String sign);

    Integer removeDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("sign") String sign);

    Integer removeAllByWhere(@Param("whereSql") String whereSql, @Param("sign") String sign);

    Integer removeDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql,@Param("sign") String sign);
}
