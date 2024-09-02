package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.filter.PageFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>FilterMapper</code>
 * <p>The type filter mapper interface.</p>
 * @param <E> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface FilterMapper<E> {

    /**
     * <code>operateAllByFilterWhere</code>
     * <p>the all by filter where method.</p>
     * @param whereSql {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter   {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>the filter parameter is <code>PageFilter</code> type.</p>
     * @param operate  {@link java.lang.Integer} <p>the operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rice.filter.PageFilter
     * @see java.lang.Integer
     */
    Integer operateAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("operate") Integer operate);

    /**
     * <code>operateDynamicAllByFilterWhere</code>
     * <p>the dynamic all by filter where method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter    {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>the filter parameter is <code>PageFilter</code> type.</p>
     * @param operate   {@link java.lang.Integer} <p>the operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rice.filter.PageFilter
     * @see java.lang.Integer
     */
    Integer operateDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("operate") Integer operate);

    /**
     * <code>removeAllByFilterWhere</code>
     * <p>the all by filter where method.</p>
     * @param whereSql {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter   {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>the filter parameter is <code>PageFilter</code> type.</p>
     * @param sign     {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rice.filter.PageFilter
     * @see java.lang.Integer
     */
    Integer removeAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("sign") String sign);

    /**
     * <code>removeDynamicAllByFilterWhere</code>
     * <p>the dynamic all by filter where method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter    {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>the filter parameter is <code>PageFilter</code> type.</p>
     * @param sign      {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rice.filter.PageFilter
     * @see java.lang.Integer
     */
    Integer removeDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter, @Param("sign") String sign);

    /**
     * <code>findAllByFilterWhere</code>
     * <p>the all by filter where method.</p>
     * @param whereSql {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter   {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>the filter parameter is <code>PageFilter</code> type.</p>
     * @return {@link java.util.List} <p>the all by filter where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rice.filter.PageFilter
     * @see java.util.List
     */
    List<E> findAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter);

    /**
     * <code>findDynamicAllByFilterWhere</code>
     * <p>the dynamic all by filter where method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter    {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>the filter parameter is <code>PageFilter</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic all by filter where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rice.filter.PageFilter
     * @see java.util.List
     */
    List<E> findDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter);

    /**
     * <code>deleteAllByFilterWhere</code>
     * <p>the all by filter where method.</p>
     * @param whereSql {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter   {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>the filter parameter is <code>PageFilter</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rice.filter.PageFilter
     * @see java.lang.Integer
     */
    Integer deleteAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") PageFilter filter);

    /**
     * <code>deleteDynamicAllByFilterWhere</code>
     * <p>the dynamic all by filter where method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter    {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>the filter parameter is <code>PageFilter</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rice.filter.PageFilter
     * @see java.lang.Integer
     */
    Integer deleteDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") PageFilter filter);
}
