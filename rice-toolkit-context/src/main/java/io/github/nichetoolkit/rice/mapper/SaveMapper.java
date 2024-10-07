package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>SaveMapper</code>
 * <p>The type save mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface SaveMapper<E extends RestId<I>,I> {

    /**
     * <code>save</code>
     * <p>The method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @return {@link java.lang.Integer} <p>The return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer save(@Param("entity") E entity);

    /**
     * <code>saveDynamic</code>
     * <p>The dynamic method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param entity    E <p>The entity parameter is <code>E</code> type.</p>
     * @return {@link java.lang.Integer} <p>The dynamic return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer saveDynamic(@Param("tablename") String tablename, @Param("entity") E entity);

    /**
     * <code>saveAll</code>
     * <p>The all method.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>The all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer saveAll(@Param("entityList") Collection<E> entityList);

    /**
     * <code>saveDynamicAll</code>
     * <p>The dynamic all method.</p>
     * @param tablename  {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param entityList {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>The dynamic all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer saveDynamicAll(@Param("tablename") String tablename, @Param("entityList") Collection<E> entityList);
}
