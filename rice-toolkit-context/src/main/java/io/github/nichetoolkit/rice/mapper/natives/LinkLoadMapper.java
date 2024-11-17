package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <code>LinkLoadMapper</code>
 * <p>The link load mapper interface.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <L>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface LinkLoadMapper<E extends RestId<I>, L, I> {
    /**
     * <code>findByLinkIdLoad</code>
     * <p>The find by link id load method.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Boolean
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find by link id load return object is <code>List</code> type.</p>
     */
    List<E> findByLinkIdLoad(@Param("linkId") L linkId, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findDynamicByLinkIdLoad</code>
     * <p>The find dynamic by link id load method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Boolean
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find dynamic by link id load return object is <code>List</code> type.</p>
     */
    List<E> findDynamicByLinkIdLoad(@Param("tablename") String tablename, @Param("id") L linkId, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findAllByLinkIdsLoad</code>
     * <p>The find all by link ids load method.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @see  java.util.Collection
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Boolean
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find all by link ids load return object is <code>List</code> type.</p>
     */
    List<E> findAllByLinkIdsLoad(@Param("linkIdList") Collection<L> linkIdList, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findDynamicAllByLinkIdsLoad</code>
     * <p>The find dynamic all by link ids load method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.util.Collection
     * @see  java.lang.Boolean
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find dynamic all by link ids load return object is <code>List</code> type.</p>
     */
    List<E> findDynamicAllByLinkIdsLoad(@Param("tablename") String tablename, @Param("linkIdList") Collection<L> linkIdList, @Param("loadParams") Boolean... loadParams);

}
