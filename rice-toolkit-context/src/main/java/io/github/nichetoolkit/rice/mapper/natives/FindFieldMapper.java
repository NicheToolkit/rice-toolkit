package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>FindFieldMapper</code>
 * <p>The type find field mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @since Jdk1.8
 */
public interface FindFieldMapper<E extends RestId<I>, I> {

    /**
     * <code>findAllByFieldWhere</code>
     * <p>the all by field where method.</p>
     * @param whereSql    {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param fieldParams {@link java.lang.String} <p>the field params parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the all by field where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findAllByFieldWhere(@Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams);

    /**
     * <code>findDynamicAllByFieldWhere</code>
     * <p>the dynamic all by field where method.</p>
     * @param tablename   {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param whereSql    {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param fieldParams {@link java.lang.String} <p>the field params parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic all by field where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicAllByFieldWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams);

}
