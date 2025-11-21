package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.mybatis.fickle.RestFickle;
import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <code>LinkFickleMapper</code>
 * <p>The link fickle mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <L> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @since Jdk1.8
 */
public interface LinkFickleMapper<E extends RestId<I>, L, I> {
    /**
     * <code>findByLinkIdFickle</code>
     * <p>The find by link id fickle method.</p>
     * @param linkId       L <p>The link id parameter is <code>L</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find by link id fickle return object is <code>List</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     */
    List<E> findByLinkIdFickle(@Param("linkId") L linkId, @Param("fickleParams") RestFickle<?>... fickleParams);

    /**
     * <code>findDynamicByLinkIdFickle</code>
     * <p>The find dynamic by link id fickle method.</p>
     * @param tableName    {@link java.lang.String} <p>The tableName parameter is <code>String</code> type.</p>
     * @param linkId       L <p>The link id parameter is <code>L</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic by link id fickle return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     */
    List<E> findDynamicByLinkIdFickle(@Param("tableName") String tableName, @Param("linkId") L linkId, @Param("fickleParams") RestFickle<?>... fickleParams);

    /**
     * <code>findAllByLinkIdsFickle</code>
     * <p>The find all by link ids fickle method.</p>
     * @param linkIdList   {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find all by link ids fickle return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     */
    List<E> findAllByLinkIdsFickle(@Param("linkIdList") Collection<L> linkIdList, @Param("fickleParams") RestFickle<?>... fickleParams);

    /**
     * <code>findDynamicAllByLinkIdsFickle</code>
     * <p>The find dynamic all by link ids fickle method.</p>
     * @param tableName    {@link java.lang.String} <p>The tableName parameter is <code>String</code> type.</p>
     * @param linkIdList   {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic all by link ids fickle return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     */
    List<E> findDynamicAllByLinkIdsFickle(@Param("tableName") String tableName, @Param("linkIdList") Collection<L> linkIdList, @Param("fickleParams") RestFickle<?>... fickleParams);

    /**
     * <code>findByLinkIdFickle</code>
     * <p>The find by link id fickle method.</p>
     * @param linkId       L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName     {@link java.lang.String} <p>The link name parameter is <code>String</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find by link id fickle return object is <code>List</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     */
    List<E> findByLinkIdFickle(@Param("linkId") L linkId, @Param("linkName") String linkName, @Param("fickleParams") RestFickle<?>... fickleParams);

    /**
     * <code>findDynamicByLinkIdFickle</code>
     * <p>The find dynamic by link id fickle method.</p>
     * @param tableName    {@link java.lang.String} <p>The tableName parameter is <code>String</code> type.</p>
     * @param linkId       L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName     {@link java.lang.String} <p>The link name parameter is <code>String</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic by link id fickle return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     */
    List<E> findDynamicByLinkIdFickle(@Param("tableName") String tableName, @Param("linkId") L linkId, @Param("linkName") String linkName, @Param("fickleParams") RestFickle<?>... fickleParams);

    /**
     * <code>findAllByLinkIdsFickle</code>
     * <p>The find all by link ids fickle method.</p>
     * @param linkIdList   {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName     {@link java.lang.String} <p>The link name parameter is <code>String</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find all by link ids fickle return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     */
    List<E> findAllByLinkIdsFickle(@Param("linkIdList") Collection<L> linkIdList, @Param("linkName") String linkName, @Param("fickleParams") RestFickle<?>... fickleParams);

    /**
     * <code>findDynamicAllByLinkIdsFickle</code>
     * <p>The find dynamic all by link ids fickle method.</p>
     * @param tableName    {@link java.lang.String} <p>The tableName parameter is <code>String</code> type.</p>
     * @param linkIdList   {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName     {@link java.lang.String} <p>The link name parameter is <code>String</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic all by link ids fickle return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     */
    List<E> findDynamicAllByLinkIdsFickle(@Param("tableName") String tableName, @Param("linkIdList") Collection<L> linkIdList, @Param("linkName") String linkName, @Param("fickleParams") RestFickle<?>... fickleParams);

}
