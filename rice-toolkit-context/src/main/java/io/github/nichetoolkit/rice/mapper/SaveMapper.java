package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>SaveMapper</code>
 * <p>The type save mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface SaveMapper<E extends RestId<I>,I> {

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
}
