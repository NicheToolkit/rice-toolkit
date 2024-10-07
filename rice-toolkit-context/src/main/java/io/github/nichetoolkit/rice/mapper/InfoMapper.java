package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RestInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>InfoMapper</code>
 * <p>The type info mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestInfo} <p>The generic parameter is <code>RestInfo</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfo
 * @see io.github.nichetoolkit.rice.mapper.SuperMapper
 * @since Jdk1.8
 */
public interface InfoMapper<E extends RestInfo<I>, I> extends SuperMapper<E, I> {
    /**
     * <code>findByName</code>
     * <p>The by name method.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param logic {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The by name return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findByName(@Param("name") String name, @Param("logic") String logic);

    /**
     * <code>findDynamicByName</code>
     * <p>The dynamic by name method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param name      {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param logic     {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The dynamic by name return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicByName(@Param("tablename") String tablename, @Param("name") String name, @Param("logic") String logic);

    /**
     * <code>findByNameAndNotId</code>
     * <p>The by name and not id method.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param id    I <p>The id parameter is <code>I</code> type.</p>
     * @param logic {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The by name and not id return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findByNameAndNotId(@Param("name") String name, @Param("id") I id, @Param("logic") String logic);

    /**
     * <code>findDynamicByNameAndNotId</code>
     * <p>The dynamic by name and not id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param name      {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param logic     {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The dynamic by name and not id return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicByNameAndNotId(@Param("tablename") String tablename, @Param("name") String name, @Param("id") I id, @Param("logic") String logic);

    /**
     * <code>findByEntity</code>
     * <p>The by entity method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @param logic  {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The by entity return object is <code>List</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.util.List
     */
    List<E> findByEntity(@Param("entity") E entity, @Param("logic") String logic);

    /**
     * <code>findDynamicByEntity</code>
     * <p>The dynamic by entity method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param entity    E <p>The entity parameter is <code>E</code> type.</p>
     * @param logic     {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The dynamic by entity return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicByEntity(@Param("tablename") String tablename, @Param("entity") E entity, @Param("logic") String logic);

    /**
     * <code>findByEntityAndNotId</code>
     * <p>The by entity and not id method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @param id     I <p>The id parameter is <code>I</code> type.</p>
     * @param logic  {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The by entity and not id return object is <code>List</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.util.List
     */
    List<E> findByEntityAndNotId(@Param("entity") E entity, @Param("id") I id, @Param("logic") String logic);

    /**
     * <code>findDynamicByEntityAndNotId</code>
     * <p>The dynamic by entity and not id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param entity    E <p>The entity parameter is <code>E</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param logic     {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The dynamic by entity and not id return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicByEntityAndNotId(@Param("tablename") String tablename, @Param("entity") E entity, @Param("id") I id, @Param("logic") String logic);
}
