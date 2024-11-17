package io.github.nichetoolkit.rice.mapper.natives;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>FilterLoadMapper</code>
 * <p>The filter load mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @since Jdk1.8
 */
public interface FilterLoadMapper<E extends RestId<I>, I> {

    /**
     * <code>findAllByLoadWhere</code>
     * <p>The find all by load where method.</p>
     * @param whereSql   {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>The find all by load where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Boolean
     * @see java.util.List
     */
    List<E> findAllByLoadWhere(@Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findDynamicAllByLoadWhere</code>
     * <p>The find dynamic all by load where method.</p>
     * @param tablename  {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param whereSql   {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic all by load where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Boolean
     * @see java.util.List
     */
    List<E> findDynamicAllByLoadWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams);

}
