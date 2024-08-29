package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface RemoveLinkMapper<I> extends RemoveMapper<I> {

    Integer removeByLinkId(@Param("linkId") I linkId, @Param("sign") String sign);

    Integer removeDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") I linkId, @Param("sign") String sign);

    Integer removeAllByLinkIds(@Param("linkIdList") Collection<I> linkIdList, @Param("sign") String sign);

    Integer removeDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<I> linkIdList, @Param("sign") String sign);

}
