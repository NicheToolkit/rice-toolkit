package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>FickleFilterMapper</code>
 * <p>The fickle filter mapper interface.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface FickleFilterMapper<E extends RestId<I>, I> {

    /**
     * <code>findAllByFickleLoadWhere</code>
     * <p>The find all by fickle load where method.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param fickleParams {@link java.lang.String} <p>The fickle params parameter is <code>String</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Boolean
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find all by fickle load where return object is <code>List</code> type.</p>
     */
    List<E> findAllByFickleLoadWhere(@Param("whereSql") String whereSql, @Param("fickleParams") String[] fickleParams, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findDynamicAllByFickleLoadWhere</code>
     * <p>The find dynamic all by fickle load where method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param fickleParams {@link java.lang.String} <p>The fickle params parameter is <code>String</code> type.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Boolean
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find dynamic all by fickle load where return object is <code>List</code> type.</p>
     */
    List<E> findDynamicAllByFickleLoadWhere(@Param("tablename") String tablename, @Param("fickleParams") String[] fickleParams, @Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams);

}
