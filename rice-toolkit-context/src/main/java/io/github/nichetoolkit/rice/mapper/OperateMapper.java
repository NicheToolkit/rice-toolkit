package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>OperateMapper</code>
 * <p>The type operate mapper interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface OperateMapper<I> {
    /**
     * <code>operateById</code>
     * <p>The by id method.</p>
     * @param id      I <p>The id parameter is <code>I</code> type.</p>
     * @param operate {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The by id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer operateById(@Param("id") I id, @Param("operate") Integer operate);

    /**
     * <code>operateDynamicById</code>
     * <p>The dynamic by id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param operate   {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The dynamic by id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer operateDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("operate") Integer operate);

    /**
     * <code>operateAll</code>
     * <p>The all method.</p>
     * @param idList  {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param operate {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer operateAll(@Param("idList") Collection<I> idList, @Param("operate") Integer operate);

    /**
     * <code>operateDynamicAll</code>
     * <p>The dynamic all method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param operate   {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The dynamic all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer operateDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("operate") Integer operate);

    /**
     * <code>operateAllByWhere</code>
     * <p>The all by where method.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param operate  {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer operateAllByWhere(@Param("whereSql") String whereSql, @Param("operate") Integer operate);

    /**
     * <code>operateDynamicAllByWhere</code>
     * <p>The dynamic all by where method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param operate   {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The dynamic all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer operateDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("operate") Integer operate);
}
