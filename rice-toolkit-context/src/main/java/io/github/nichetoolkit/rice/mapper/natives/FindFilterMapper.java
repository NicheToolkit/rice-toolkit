package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.filter.IdFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>FindFilterMapper</code>
 * <p>The type find filter mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>the generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @since Jdk1.8
 */
public interface FindFilterMapper<E extends IdEntity<I>, F extends IdFilter<I, K>, I, K> {
    /**
     * <code>findAllByFilterWhere</code>
     * <p>the all by filter where method.</p>
     * @param whereSql {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter   F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link java.util.List} <p>the all by filter where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter);

    /**
     * <code>findDynamicAllByFilterWhere</code>
     * <p>the dynamic all by filter where method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param filter    F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic all by filter where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter);
}
