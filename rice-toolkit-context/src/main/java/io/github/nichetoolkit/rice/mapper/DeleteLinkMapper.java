package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface DeleteLinkMapper<I> {

    Integer deleteByLinkId(@Param("linkId") I linkId);

    Integer deleteDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") I linkId);

    Integer deleteAllByLinkIds(@Param("linkIdList") Collection<I> linkIdList);

    Integer deleteDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<I> linkIdList);

}
