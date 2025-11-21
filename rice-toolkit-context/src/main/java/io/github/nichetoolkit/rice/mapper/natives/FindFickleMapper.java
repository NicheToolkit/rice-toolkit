package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.mybatis.fickle.RestFickle;
import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <code>FindFickleMapper</code>
 * <p>The find fickle mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @since Jdk1.8
 */
public interface FindFickleMapper<E extends RestId<I>, I> {
    /**
     * <code>findByIdFickle</code>
     * <p>The find by id fickle method.</p>
     * @param id           I <p>The id parameter is <code>I</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return E <p>The find by id fickle return object is <code>E</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     */
    E findByIdFickle(@Param("id") I id, @Param("fickleParams") RestFickle<?>... fickleParams);

    /**
     * <code>findDynamicByIdFickle</code>
     * <p>The find dynamic by id fickle method.</p>
     * @param tableName    {@link java.lang.String} <p>The tableName parameter is <code>String</code> type.</p>
     * @param id           I <p>The id parameter is <code>I</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return E <p>The find dynamic by id fickle return object is <code>E</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     */
    E findDynamicByIdFickle(@Param("tableName") String tableName, @Param("id") I id, @Param("fickleParams") RestFickle<?>... fickleParams);

    /**
     * <code>findAllFickle</code>
     * <p>The find all fickle method.</p>
     * @param idList       {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find all fickle return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     */
    List<E> findAllFickle(@Param("idList") Collection<I> idList, @Param("fickleParams") RestFickle<?>... fickleParams);

    /**
     * <code>findDynamicAllFickle</code>
     * <p>The find dynamic all fickle method.</p>
     * @param tableName    {@link java.lang.String} <p>The tableName parameter is <code>String</code> type.</p>
     * @param idList       {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic all fickle return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     */
    List<E> findDynamicAllFickle(@Param("tableName") String tableName, @Param("idList") Collection<I> idList, @Param("fickleParams") RestFickle<?>... fickleParams);

}
