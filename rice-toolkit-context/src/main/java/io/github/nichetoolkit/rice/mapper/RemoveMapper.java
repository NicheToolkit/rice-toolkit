package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>RemoveMapper</code>
 * <p>The remove mapper interface.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RemoveMapper<I> {

    /**
     * <code>removeById</code>
     * <p>The remove by id method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove by id return object is <code>Integer</code> type.</p>
     */
    Integer removeById(@Param("id") I id, @Param("logic") Object logic);

    /**
     * <code>removeDynamicById</code>
     * <p>The remove dynamic by id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove dynamic by id return object is <code>Integer</code> type.</p>
     */
    Integer removeDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("logic") Object logic);

    /**
     * <code>removeAll</code>
     * <p>The remove all method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.util.Collection
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove all return object is <code>Integer</code> type.</p>
     */
    Integer removeAll(@Param("idList") Collection<I> idList, @Param("logic") Object logic);

    /**
     * <code>removeDynamicAll</code>
     * <p>The remove dynamic all method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.util.Collection
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove dynamic all return object is <code>Integer</code> type.</p>
     */
    Integer removeDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("logic") Object logic);

    /**
     * <code>removeAllByWhere</code>
     * <p>The remove all by where method.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove all by where return object is <code>Integer</code> type.</p>
     */
    Integer removeAllByWhere(@Param("whereSql") String whereSql, @Param("logic") Object logic);

    /**
     * <code>removeDynamicAllByWhere</code>
     * <p>The remove dynamic all by where method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove dynamic all by where return object is <code>Integer</code> type.</p>
     */
    Integer removeDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("logic") Object logic);
}
