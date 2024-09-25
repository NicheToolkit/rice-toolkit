package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>RemoveMapper</code>
 * <p>The type remove mapper interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RemoveMapper<I> {

    /**
     * <code>removeById</code>
     * <p>the by id method.</p>
     * @param id        I <p>the id parameter is <code>I</code> type.</p>
     * @param logic {@link java.lang.String} <p>the logic sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the by id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.lang.Integer
     */
    Integer removeById(@Param("id") I id, @Param("logic") String logic);

    /**
     * <code>removeDynamicById</code>
     * <p>the dynamic by id method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param id        I <p>the id parameter is <code>I</code> type.</p>
     * @param logic {@link java.lang.String} <p>the logic sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic by id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer removeDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("logic") String logic);

    /**
     * <code>removeAll</code>
     * <p>the all method.</p>
     * @param idList {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param sign   {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.lang.Integer
     */
    Integer removeAll(@Param("idList") Collection<I> idList, @Param("sign") String sign);

    /**
     * <code>removeDynamicAll</code>
     * <p>the dynamic all method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param logic {@link java.lang.String} <p>the logic sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer removeDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("logic") String logic);

    /**
     * <code>removeAllByWhere</code>
     * <p>the all by where method.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param logic {@link java.lang.String} <p>the logic sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer removeAllByWhere(@Param("whereSql") String whereSql, @Param("logic") String logic);

    /**
     * <code>removeDynamicAllByWhere</code>
     * <p>the dynamic all by where method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param logic {@link java.lang.String} <p>the logic sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer removeDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql,@Param("logic") String logic);
}
