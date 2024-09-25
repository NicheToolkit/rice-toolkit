package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.filter.IdFilter;
import org.apache.ibatis.annotations.Param;

/**
 * <code>OperateFilterMapper</code>
 * <p>The type operate filter mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>the generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see io.github.nichetoolkit.rice.mapper.natives.FindFilterMapper
 * @since Jdk1.8
 */
public interface OperateFilterMapper<E extends RestId<I>, F extends IdFilter<I, K>, I, K> extends FindFilterMapper<E, F, I, K> {

    /**
     * <code>operateAllByFilterWhere</code>
     * <p>the all by filter where method.</p>
     * @param whereSql {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter   F <p>the filter parameter is <code>F</code> type.</p>
     * @param operate  {@link java.lang.Integer} <p>the operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer operateAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("operate") Integer operate);

    /**
     * <code>operateDynamicAllByFilterWhere</code>
     * <p>the dynamic all by filter where method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter    F <p>the filter parameter is <code>F</code> type.</p>
     * @param operate   {@link java.lang.Integer} <p>the operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer operateDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("operate") Integer operate);
}
