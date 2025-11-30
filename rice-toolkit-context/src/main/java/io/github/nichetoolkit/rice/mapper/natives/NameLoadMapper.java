package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.mybatis.load.RestLoad;
import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>NameLoadMapper</code>
 * <p>The name load mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @since Jdk17
 */
public interface NameLoadMapper<E extends RestId<I>, I> {
    /**
     * <code>findByNameLoad</code>
     * <p>The find by name load method.</p>
     * @param name       {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param logic      {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @param loadParams {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The load params parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The find by name load return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Object
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     */
    List<E> findByNameLoad(@Param("name") String name, @Param("logic") Object logic, @Param("loadParams") RestLoad... loadParams);

    /**
     * <code>findDynamicByNameLoad</code>
     * <p>The find dynamic by name load method.</p>
     * @param tableName  {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param name       {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param logic      {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @param loadParams {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The load params parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic by name load return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Object
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     */
    List<E> findDynamicByNameLoad(@Param("tableName") String tableName, @Param("name") String name, @Param("logic") Object logic, @Param("loadParams") RestLoad... loadParams);

}
