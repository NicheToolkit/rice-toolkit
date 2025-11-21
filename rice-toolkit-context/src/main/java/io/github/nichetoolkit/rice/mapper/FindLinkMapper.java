package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <code>FindLinkMapper</code>
 * <p>The find link mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <L> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface FindLinkMapper<E extends RestId<I>, L, I> {
    /**
     * <code>findByLinkId</code>
     * <p>The find by link id method.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @return {@link java.util.List} <p>The find by link id return object is <code>List</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findByLinkId(@Param("linkId") L linkId);

    /**
     * <code>findDynamicByLinkId</code>
     * <p>The find dynamic by link id method.</p>
     * @param tableName {@link java.lang.String} <p>The tableName parameter is <code>String</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic by link id return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicByLinkId(@Param("tableName") String tableName, @Param("linkId") L linkId);

    /**
     * <code>findAllByLinkIds</code>
     * <p>The find all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>The find all by link ids return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findAllByLinkIds(@Param("linkIdList") Collection<L> linkIdList);

    /**
     * <code>findDynamicAllByLinkIds</code>
     * <p>The find dynamic all by link ids method.</p>
     * @param tableName  {@link java.lang.String} <p>The tableName parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic all by link ids return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.util.List
     */
    List<E> findDynamicAllByLinkIds(@Param("tableName") String tableName, @Param("linkIdList") Collection<L> linkIdList);

    /**
     * <code>findByLinkId</code>
     * <p>The find by link id method.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link java.lang.String} <p>The link name parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The find by link id return object is <code>List</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.util.List
     */
    List<E> findByLinkId(@Param("linkId") L linkId, @Param("linkName") String linkName);

    /**
     * <code>findDynamicByLinkId</code>
     * <p>The find dynamic by link id method.</p>
     * @param tableName {@link java.lang.String} <p>The tableName parameter is <code>String</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName  {@link java.lang.String} <p>The link name parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic by link id return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicByLinkId(@Param("tableName") String tableName, @Param("linkId") L linkId, @Param("linkName") String linkName);

    /**
     * <code>findAllByLinkIds</code>
     * <p>The find all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link java.lang.String} <p>The link name parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The find all by link ids return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.util.List
     */
    List<E> findAllByLinkIds(@Param("linkIdList") Collection<L> linkIdList, @Param("linkName") String linkName);

    /**
     * <code>findDynamicAllByLinkIds</code>
     * <p>The find dynamic all by link ids method.</p>
     * @param tableName  {@link java.lang.String} <p>The tableName parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link java.lang.String} <p>The link name parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic all by link ids return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.util.List
     */
    List<E> findDynamicAllByLinkIds(@Param("tableName") String tableName, @Param("linkIdList") Collection<L> linkIdList, @Param("linkName") String linkName);

}
