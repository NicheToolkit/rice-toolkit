package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <code>FindLoadMapper</code>
 * <p>The find load mapper interface.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface FindLoadMapper<E extends RestId<I>, I> {
    /**
     * <code>findByIdLoad</code>
     * <p>The find by id load method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Boolean
     * @return E <p>The find by id load return object is <code>E</code> type.</p>
     */
    E findByIdLoad(@Param("id") I id, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findDynamicByIdLoad</code>
     * <p>The find dynamic by id load method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Boolean
     * @return E <p>The find dynamic by id load return object is <code>E</code> type.</p>
     */
    E findDynamicByIdLoad(@Param("tablename") String tablename, @Param("id") I id, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findAllLoad</code>
     * <p>The find all load method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @see  java.util.Collection
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Boolean
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find all load return object is <code>List</code> type.</p>
     */
    List<E> findAllLoad(@Param("idList") Collection<I> idList, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findDynamicAllLoad</code>
     * <p>The find dynamic all load method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>The load params parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.util.Collection
     * @see  java.lang.Boolean
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The find dynamic all load return object is <code>List</code> type.</p>
     */
    List<E> findDynamicAllLoad(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("loadParams") Boolean... loadParams);

}
