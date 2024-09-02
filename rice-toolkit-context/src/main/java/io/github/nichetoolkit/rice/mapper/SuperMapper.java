package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <code>SuperMapper</code>
 * <p>The type super mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface SuperMapper<E extends IdEntity<I>,I> {

    /**
     * <code>save</code>
     * <p>the method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @return {@link java.lang.Integer} <p>the return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer save(@Param("entity") E entity);

    /**
     * <code>saveDynamic</code>
     * <p>the dynamic method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param entity    E <p>the entity parameter is <code>E</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer saveDynamic(@Param("tablename") String tablename, @Param("entity") E entity);

    /**
     * <code>saveAll</code>
     * <p>the all method.</p>
     * @param entityList {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer saveAll(@Param("entityList") Collection<E> entityList);

    /**
     * <code>saveDynamicAll</code>
     * <p>the dynamic all method.</p>
     * @param tablename  {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param entityList {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer saveDynamicAll(@Param("tablename") String tablename, @Param("entityList") Collection<E> entityList);

    /**
     * <code>deleteById</code>
     * <p>the by id method.</p>
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     * @return {@link java.lang.Integer} <p>the by id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteById(@Param("id") I id);

    /**
     * <code>deleteDynamicById</code>
     * <p>the dynamic by id method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param id        I <p>the id parameter is <code>I</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic by id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteDynamicById(@Param("tablename") String tablename, @Param("id") I id);

    /**
     * <code>deleteAll</code>
     * <p>the all method.</p>
     * @param idList {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteAll(@Param("idList") Collection<I> idList);

    /**
     * <code>deleteDynamicAll</code>
     * <p>the dynamic all method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer deleteDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList);

    /**
     * <code>findById</code>
     * <p>the by id method.</p>
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     * @return E <p>the by id return object is <code>E</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     */
    E findById(@Param("id") I id);

    /**
     * <code>findDynamicById</code>
     * <p>the dynamic by id method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param id        I <p>the id parameter is <code>I</code> type.</p>
     * @return E <p>the dynamic by id return object is <code>E</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     */
    E findDynamicById(@Param("tablename") String tablename, @Param("id") I id);

    /**
     * <code>findAll</code>
     * <p>the all method.</p>
     * @param idList {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the all return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findAll(@Param("idList") Collection<I> idList);

    /**
     * <code>findDynamicAll</code>
     * <p>the dynamic all method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic all return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.util.List
     */
    List<E> findDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList);

    /**
     * <code>findAllByWhere</code>
     * <p>the all by where method.</p>
     * @param whereSql {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the all by where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findAllByWhere(@Param("whereSql") String whereSql);

    /**
     * <code>findDynamicAllByWhere</code>
     * <p>the dynamic all by where method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic all by where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql);

    /**
     * <code>deleteAllByWhere</code>
     * <p>the all by where method.</p>
     * @param whereSql {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteAllByWhere(@Param("whereSql") String whereSql);

    /**
     * <code>deleteDynamicAllByWhere</code>
     * <p>the dynamic all by where method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql);
}
