package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>SuperMapper</code>
 * <p>The type super mapper interface.</p>
 * @param <E> {@link IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see IdEntity
 * @see SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface SaveMapper<E extends IdEntity<I>,I> {

    /**
     * <code>save</code>
     * <p>the method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @return {@link Integer} <p>the return object is <code>Integer</code> type.</p>
     * @see Param
     * @see Integer
     */
    Integer save(@Param("entity") E entity);

    /**
     * <code>saveDynamic</code>
     * <p>the dynamic method.</p>
     * @param tablename {@link String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param entity    E <p>the entity parameter is <code>E</code> type.</p>
     * @return {@link Integer} <p>the dynamic return object is <code>Integer</code> type.</p>
     * @see String
     * @see Param
     * @see Integer
     */
    Integer saveDynamic(@Param("tablename") String tablename, @Param("entity") E entity);

    /**
     * <code>saveAll</code>
     * <p>the all method.</p>
     * @param entityList {@link Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @return {@link Integer} <p>the all return object is <code>Integer</code> type.</p>
     * @see Collection
     * @see Param
     * @see Integer
     */
    Integer saveAll(@Param("entityList") Collection<E> entityList);

    /**
     * <code>saveDynamicAll</code>
     * <p>the dynamic all method.</p>
     * @param tablename  {@link String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param entityList {@link Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @return {@link Integer} <p>the dynamic all return object is <code>Integer</code> type.</p>
     * @see String
     * @see Param
     * @see Collection
     * @see Integer
     */
    Integer saveDynamicAll(@Param("tablename") String tablename, @Param("entityList") Collection<E> entityList);
}
