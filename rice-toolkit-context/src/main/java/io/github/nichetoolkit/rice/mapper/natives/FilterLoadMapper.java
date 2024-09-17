package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>FilterLoadMapper</code>
 * <p>The type filter load mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @since Jdk1.8
 */
public interface FilterLoadMapper<E extends IdEntity<I>, I> {

    /**
     * <code>findAllByLoadWhere</code>
     * <p>the all by load where method.</p>
     * @param whereSql   {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>the load params parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the all by load where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Boolean
     * @see java.util.List
     */
    List<E> findAllByLoadWhere(@Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findDynamicAllByLoadWhere</code>
     * <p>the dynamic all by load where method.</p>
     * @param tablename  {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql   {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>the load params parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic all by load where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Boolean
     * @see java.util.List
     */
    List<E> findDynamicAllByLoadWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams);

}
