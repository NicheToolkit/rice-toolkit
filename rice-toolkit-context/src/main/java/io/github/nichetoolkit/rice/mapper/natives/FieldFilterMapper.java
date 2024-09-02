package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>FieldFilterMapper</code>
 * <p>The type field filter mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @since Jdk1.8
 */
public interface FieldFilterMapper<E extends IdEntity<I>, I> {

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
