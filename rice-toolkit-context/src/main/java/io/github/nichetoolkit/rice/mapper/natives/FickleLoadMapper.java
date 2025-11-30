package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.mybatis.fickle.RestFickle;
import io.github.nichetoolkit.mybatis.load.RestLoad;
import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <code>FickleLoadMapper</code>
 * <p>The fickle load mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @since Jdk17
 */
public interface FickleLoadMapper<E extends RestId<I>, I> {
    /**
     * <code>findByIdFickleLoad</code>
     * <p>The find by id fickle load method.</p>
     * @param id           I <p>The id parameter is <code>I</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @param loadParams   {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The load params parameter is <code>RestLoad</code> type.</p>
     * @return E <p>The find by id fickle load return object is <code>E</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     */
    E findByIdFickleLoad(@Param("id") I id, @Param("fickleParams") RestFickle<?>[] fickleParams, @Param("loadParams") RestLoad... loadParams);

    /**
     * <code>findDynamicByIdFickleLoad</code>
     * <p>The find dynamic by id fickle load method.</p>
     * @param tableName    {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param id           I <p>The id parameter is <code>I</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @param loadParams   {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The load params parameter is <code>RestLoad</code> type.</p>
     * @return E <p>The find dynamic by id fickle load return object is <code>E</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     */
    E findDynamicByIdFickleLoad(@Param("tableName") String tableName, @Param("id") I id, @Param("fickleParams") RestFickle<?>[] fickleParams, @Param("loadParams") RestLoad... loadParams);

    /**
     * <code>findAllFickleLoad</code>
     * <p>The find all fickle load method.</p>
     * @param idList       {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @param loadParams   {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The load params parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The find all fickle load return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     */
    List<E> findAllFickleLoad(@Param("idList") Collection<I> idList, @Param("fickleParams") RestFickle<?>[] fickleParams, @Param("loadParams") RestLoad... loadParams);

    /**
     * <code>findDynamicAllFickleLoad</code>
     * <p>The find dynamic all fickle load method.</p>
     * @param tableName    {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param idList       {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param fickleParams {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle params parameter is <code>RestFickle</code> type.</p>
     * @param loadParams   {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The load params parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic all fickle load return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     */
    List<E> findDynamicAllFickleLoad(@Param("tableName") String tableName, @Param("idList") Collection<I> idList, @Param("fickleParams") RestFickle<?>[] fickleParams, @Param("loadParams") RestLoad... loadParams);

}
