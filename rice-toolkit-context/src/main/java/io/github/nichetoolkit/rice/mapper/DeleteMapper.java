package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>DeleteMapper</code>
 * <p>The delete mapper interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface DeleteMapper<I> {
    /**
     * <code>deleteById</code>
     * <p>The delete by id method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @return {@link java.lang.Integer} <p>The delete by id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteById(@Param("id") I id);

    /**
     * <code>deleteDynamicById</code>
     * <p>The delete dynamic by id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @return {@link java.lang.Integer} <p>The delete dynamic by id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteDynamicById(@Param("tablename") String tablename, @Param("id") I id);

    /**
     * <code>deleteAll</code>
     * <p>The delete all method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>The delete all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteAll(@Param("idList") Collection<I> idList);

    /**
     * <code>deleteDynamicAll</code>
     * <p>The delete dynamic all method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>The delete dynamic all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer deleteDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList);

    /**
     * <code>deleteAllByWhere</code>
     * <p>The delete all by where method.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The delete all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteAllByWhere(@Param("whereSql") String whereSql);

    /**
     * <code>deleteDynamicAllByWhere</code>
     * <p>The delete dynamic all by where method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The delete dynamic all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql);
}
