package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <code>FindLoadMapper</code>
 * <p>The type find load mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @since Jdk1.8
 */
public interface FindLoadMapper<E extends RestId<I>, I> {
    /**
     * <code>findByIdLoad</code>
     * <p>the by id load method.</p>
     * @param id         I <p>the id parameter is <code>I</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>the load params parameter is <code>Boolean</code> type.</p>
     * @return E <p>the by id load return object is <code>E</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Boolean
     */
    E findByIdLoad(@Param("id") I id, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findDynamicByIdLoad</code>
     * <p>the dynamic by id load method.</p>
     * @param tablename  {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param id         I <p>the id parameter is <code>I</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>the load params parameter is <code>Boolean</code> type.</p>
     * @return E <p>the dynamic by id load return object is <code>E</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Boolean
     */
    E findDynamicByIdLoad(@Param("tablename") String tablename, @Param("id") I id, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findAllLoad</code>
     * <p>the all load method.</p>
     * @param idList     {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>the load params parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the all load return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Boolean
     * @see java.util.List
     */
    List<E> findAllLoad(@Param("idList") Collection<I> idList, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findDynamicAllLoad</code>
     * <p>the dynamic all load method.</p>
     * @param tablename  {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param idList     {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>the load params parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic all load return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Boolean
     * @see java.util.List
     */
    List<E> findDynamicAllLoad(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("loadParams") Boolean... loadParams);

}
