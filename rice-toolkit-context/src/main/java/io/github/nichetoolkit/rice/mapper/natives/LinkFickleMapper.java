package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.mybatis.fickle.RestFickle;
import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface LinkFickleMapper<E extends RestId<I>, L, I> {
    List<E> findByLinkIdFickle(@Param("linkId") L linkId, @Param("fickleParams") RestFickle<?>... fickleParams);

    List<E> findDynamicByLinkIdFickle(@Param("tablename") String tablename, @Param("linkId") L linkId, @Param("fickleParams") RestFickle<?>... fickleParams);

    List<E> findAllByLinkIdsFickle(@Param("linkIdList") Collection<L> linkIdList, @Param("fickleParams") RestFickle<?>... fickleParams);

    List<E> findDynamicAllByLinkIdsFickle(@Param("tablename") String tablename, @Param("linkIdList") Collection<L> linkIdList, @Param("fickleParams") RestFickle<?>... fickleParams);

    List<E> findByLinkIdFickle(@Param("linkId") L linkId, @Param("linkName") String linkName, @Param("fickleParams") RestFickle<?>... fickleParams);

    List<E> findDynamicByLinkIdFickle(@Param("tablename") String tablename, @Param("linkId") L linkId, @Param("linkName") String linkName, @Param("fickleParams") RestFickle<?>... fickleParams);

    List<E> findAllByLinkIdsFickle(@Param("linkIdList") Collection<L> linkIdList, @Param("linkName") String linkName, @Param("fickleParams") RestFickle<?>... fickleParams);

    List<E> findDynamicAllByLinkIdsFickle(@Param("tablename") String tablename, @Param("linkIdList") Collection<L> linkIdList, @Param("linkName") String linkName, @Param("fickleParams") RestFickle<?>... fickleParams);

}
