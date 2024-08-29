package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.filter.PageFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilterMapper<E> {

    Integer operateAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("operate") Integer operate);

    Integer operateDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("operate") Integer operate);

    Integer removeAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("sign") String sign);

    Integer removeDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("sign") String sign);

    List<E> findAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter);

    List<E> findDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter);

    Integer deleteAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter);

    Integer deleteDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter);
}
