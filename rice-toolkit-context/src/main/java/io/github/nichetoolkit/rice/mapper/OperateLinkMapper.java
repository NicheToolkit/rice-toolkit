package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface OperateLinkMapper<I> extends OperateMapper<I> {
    Integer operateByLinkId(@Param("id") I linkId, @Param("operate") Integer operate);

    Integer operateDynamicByLinkId(@Param("tablename") String tablename, @Param("id") I linkId, @Param("operate") Integer operate);

    Integer operateAllByLinkIds(@Param("linkIdList") Collection<I> linkIdList, @Param("operate") Integer operate);

    Integer operateDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<I> linkIdList, @Param("operate") Integer operate);

}
