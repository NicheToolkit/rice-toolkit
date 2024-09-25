package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <code>FindMapper</code>
 * <p>The type find mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface FindMapper<E extends RestId<I>,I> {
    /**
     * <code>findById</code>
     * <p>the by id method.</p>
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     * @return E <p>the by id return object is <code>E</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     */
    E findById(@Param("id") I id);

    /**
     * <code>findDynamicById</code>
     * <p>the dynamic by id method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param id        I <p>the id parameter is <code>I</code> type.</p>
     * @return E <p>the dynamic by id return object is <code>E</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     */
    E findDynamicById(@Param("tablename") String tablename, @Param("id") I id);

    /**
     * <code>findAll</code>
     * <p>the all method.</p>
     * @param idList {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the all return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findAll(@Param("idList") Collection<I> idList);

    /**
     * <code>findDynamicAll</code>
     * <p>the dynamic all method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic all return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.util.List
     */
    List<E> findDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList);

    /**
     * <code>findAllByWhere</code>
     * <p>the all by where method.</p>
     * @param whereSql {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the all by where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findAllByWhere(@Param("whereSql") String whereSql);

    /**
     * <code>findDynamicAllByWhere</code>
     * <p>the dynamic all by where method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic all by where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql);
}
