package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.filter.IdFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>FindFilterMapper</code>
 * <p>The find filter mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @since Jdk1.8
 */
public interface FindFilterMapper<E extends RestId<I>, F extends IdFilter<I, K>, I, K> {
    /**
     * <code>findAllByFilterWhere</code>
     * <p>The find all by filter where method.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param filter   F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link java.util.List} <p>The find all by filter where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter);

    /**
     * <code>findDynamicAllByFilterWhere</code>
     * <p>The find dynamic all by filter where method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param filter    F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic all by filter where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter);
}
