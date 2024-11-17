package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RestInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>InfoMapper</code>
 * <p>The info mapper interface.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestInfo} <p>The generic parameter is <code>RestInfo</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestInfo
 * @see  io.github.nichetoolkit.rice.mapper.SuperMapper
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface InfoMapper<E extends RestInfo<I>, I> extends SuperMapper<E, I> {
    /**
     * <code>findByName</code>
     * <p>The find by name method.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find by name return object is <code>List</code> type.</p>
     */
    List<E> findByName(@Param("name") String name, @Param("logic") Object logic);

    /**
     * <code>findDynamicByName</code>
     * <p>The find dynamic by name method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find dynamic by name return object is <code>List</code> type.</p>
     */
    List<E> findDynamicByName(@Param("tablename") String tablename, @Param("name") String name, @Param("logic") Object logic);

    /**
     * <code>findByNameAndNotId</code>
     * <p>The find by name and not id method.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find by name and not id return object is <code>List</code> type.</p>
     */
    List<E> findByNameAndNotId(@Param("name") String name, @Param("id") I id, @Param("logic") Object logic);

    /**
     * <code>findDynamicByNameAndNotId</code>
     * <p>The find dynamic by name and not id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find dynamic by name and not id return object is <code>List</code> type.</p>
     */
    List<E> findDynamicByNameAndNotId(@Param("tablename") String tablename, @Param("name") String name, @Param("id") I id, @Param("logic") Object logic);

    /**
     * <code>findByEntityUnique</code>
     * <p>The find by entity unique method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find by entity unique return object is <code>List</code> type.</p>
     */
    List<E> findByEntityUnique(@Param("entity") E entity, @Param("logic") Object logic);

    /**
     * <code>findDynamicByEntityUnique</code>
     * <p>The find dynamic by entity unique method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find dynamic by entity unique return object is <code>List</code> type.</p>
     */
    List<E> findDynamicByEntityUnique(@Param("tablename") String tablename, @Param("entity") E entity, @Param("logic") Object logic);

    /**
     * <code>findByEntityUniqueAndNotId</code>
     * <p>The find by entity unique and not id method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find by entity unique and not id return object is <code>List</code> type.</p>
     */
    List<E> findByEntityUniqueAndNotId(@Param("entity") E entity, @Param("id") I id, @Param("logic") Object logic);

    /**
     * <code>findDynamicByEntityUniqueAndNotId</code>
     * <p>The find dynamic by entity unique and not id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find dynamic by entity unique and not id return object is <code>List</code> type.</p>
     */
    List<E> findDynamicByEntityUniqueAndNotId(@Param("tablename") String tablename, @Param("entity") E entity, @Param("id") I id, @Param("logic") Object logic);
}
