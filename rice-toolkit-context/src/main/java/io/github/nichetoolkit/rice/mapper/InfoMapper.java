package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.InfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>InfoMapper</code>
 * <p>The type info mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.InfoEntity} <p>the generic parameter is <code>InfoEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.InfoEntity
 * @see io.github.nichetoolkit.rice.mapper.SuperMapper
 * @since Jdk1.8
 */
public interface InfoMapper<E extends InfoEntity<I>, I> extends SuperMapper<E, I> {
    /**
     * <code>findByName</code>
     * <p>the by name method.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param sign {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the by name return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findByName(@Param("name") String name, @Param("sign") String sign);

    /**
     * <code>findDynamicByName</code>
     * <p>the dynamic by name method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param name      {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param sign      {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic by name return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicByName(@Param("tablename") String tablename, @Param("name") String name, @Param("sign") String sign);

    /**
     * <code>findByNameAndNotId</code>
     * <p>the by name and not id method.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param id   I <p>the id parameter is <code>I</code> type.</p>
     * @param sign {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the by name and not id return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findByNameAndNotId(@Param("name") String name, @Param("id") I id, @Param("sign") String sign);

    /**
     * <code>findDynamicByNameAndNotId</code>
     * <p>the dynamic by name and not id method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param name      {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param id        I <p>the id parameter is <code>I</code> type.</p>
     * @param sign      {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic by name and not id return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicByNameAndNotId(@Param("tablename") String tablename, @Param("name") String name, @Param("id") I id, @Param("sign") String sign);

    /**
     * <code>findByEntity</code>
     * <p>the by entity method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @param sign   {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the by entity return object is <code>List</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.util.List
     */
    List<E> findByEntity(@Param("entity") E entity, @Param("sign") String sign);

    /**
     * <code>findDynamicByEntity</code>
     * <p>the dynamic by entity method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param entity    E <p>the entity parameter is <code>E</code> type.</p>
     * @param sign      {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic by entity return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicByEntity(@Param("tablename") String tablename, @Param("entity") E entity, @Param("sign") String sign);

    /**
     * <code>findByEntityAndNotId</code>
     * <p>the by entity and not id method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @param id     I <p>the id parameter is <code>I</code> type.</p>
     * @param sign   {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the by entity and not id return object is <code>List</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.util.List
     */
    List<E> findByEntityAndNotId(@Param("entity") E entity, @Param("id") I id, @Param("sign") String sign);

    /**
     * <code>findDynamicByEntityAndNotId</code>
     * <p>the dynamic by entity and not id method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param entity    E <p>the entity parameter is <code>E</code> type.</p>
     * @param id        I <p>the id parameter is <code>I</code> type.</p>
     * @param sign      {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic by entity and not id return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicByEntityAndNotId(@Param("tablename") String tablename, @Param("entity") E entity, @Param("id") I id, @Param("sign") String sign);
}
