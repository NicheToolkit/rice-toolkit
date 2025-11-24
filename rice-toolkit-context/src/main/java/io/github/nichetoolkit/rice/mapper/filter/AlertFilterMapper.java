package io.github.nichetoolkit.rice.mapper.filter;

import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.mapper.AlertMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <code>AlertFilterMapper</code>
 * <p>The alert filter mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
 * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see io.github.nichetoolkit.rice.mapper.filter.FindFilterMapper
 * @see io.github.nichetoolkit.rice.mapper.AlertMapper
 * @since Jdk1.8
 */
public interface AlertFilterMapper<E extends RestId<I>, F extends IdFilter<I, K>, S, I, K> extends FindFilterMapper<E, F, I, K>, AlertMapper<S,I> {

    /**
     * <code>alertAllByFilterWhere</code>
     * <p>The alert all by filter where method.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param filter   F <p>The filter parameter is <code>F</code> type.</p>
     * @param state    S <p>The state parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("state") S state);

    /**
     * <code>alertDynamicAllByFilterWhere</code>
     * <p>The alert dynamic all by filter where method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param filter    F <p>The filter parameter is <code>F</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert dynamic all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertDynamicAllByFilterWhere(@Param("tableName") String tableName, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("state") S state);


    /**
     * <code>alertAllByFilterWhere</code>
     * <p>The alert all by filter where method.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param filter    F <p>The filter parameter is <code>F</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link java.lang.String} <p>The state name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("state") S state, @Param("stateName") String stateName);

    /**
     * <code>alertDynamicAllByFilterWhere</code>
     * <p>The alert dynamic all by filter where method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param filter    F <p>The filter parameter is <code>F</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link java.lang.String} <p>The state name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert dynamic all by filter where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertDynamicAllByFilterWhere(@Param("tableName") String tableName, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("state") S state, @Param("stateName") String stateName);
}
