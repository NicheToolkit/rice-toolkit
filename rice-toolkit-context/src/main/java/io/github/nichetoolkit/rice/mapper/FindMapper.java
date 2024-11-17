package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <code>FindMapper</code>
 * <p>The find mapper interface.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface FindMapper<E extends RestId<I>,I> {
    /**
     * <code>findById</code>
     * <p>The find by id method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @see  org.apache.ibatis.annotations.Param
     * @return E <p>The find by id return object is <code>E</code> type.</p>
     */
    E findById(@Param("id") I id);

    /**
     * <code>findDynamicById</code>
     * <p>The find dynamic by id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @return E <p>The find dynamic by id return object is <code>E</code> type.</p>
     */
    E findDynamicById(@Param("tablename") String tablename, @Param("id") I id);

    /**
     * <code>findAll</code>
     * <p>The find all method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find all return object is <code>List</code> type.</p>
     */
    List<E> findAll(@Param("idList") Collection<I> idList);

    /**
     * <code>findDynamicAll</code>
     * <p>The find dynamic all method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.util.Collection
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find dynamic all return object is <code>List</code> type.</p>
     */
    List<E> findDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList);

    /**
     * <code>findAllByWhere</code>
     * <p>The find all by where method.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find all by where return object is <code>List</code> type.</p>
     */
    List<E> findAllByWhere(@Param("whereSql") String whereSql);

    /**
     * <code>findDynamicAllByWhere</code>
     * <p>The find dynamic all by where method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find dynamic all by where return object is <code>List</code> type.</p>
     */
    List<E> findDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql);
}
