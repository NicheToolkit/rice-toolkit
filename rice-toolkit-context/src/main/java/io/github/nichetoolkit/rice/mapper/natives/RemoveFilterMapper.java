package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.filter.IdFilter;
import org.apache.ibatis.annotations.Param;

/**
 * <code>RemoveFilterMapper</code>
 * <p>The remove filter mapper interface.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <F>  {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @see  io.github.nichetoolkit.rice.filter.IdFilter
 * @see  io.github.nichetoolkit.rice.mapper.natives.FindFilterMapper
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RemoveFilterMapper<E extends RestId<I>, F extends IdFilter<I, K>, I, K> extends FindFilterMapper<E, F, I, K> {

    /**
     * <code>removeAllByFilterWhere</code>
     * <p>The remove all by filter where method.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove all by filter where return object is <code>Integer</code> type.</p>
     */
    Integer removeAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("logic") Object logic);

    /**
     * <code>removeDynamicAllByFilterWhere</code>
     * <p>The remove dynamic all by filter where method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove dynamic all by filter where return object is <code>Integer</code> type.</p>
     */
    Integer removeDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("logic") Object logic);
}
