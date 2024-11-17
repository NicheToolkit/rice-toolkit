package io.github.nichetoolkit.rice.mapper.natives;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>FindFieldMapper</code>
 * <p>The find field mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @since Jdk1.8
 */
public interface FindFieldMapper<E extends RestId<I>, I> {

    /**
     * <code>findAllByFieldWhere</code>
     * <p>The find all by field where method.</p>
     * @param whereSql    {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param fieldParams {@link java.lang.String} <p>The field params parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The find all by field where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findAllByFieldWhere(@Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams);

    /**
     * <code>findDynamicAllByFieldWhere</code>
     * <p>The find dynamic all by field where method.</p>
     * @param tablename   {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param whereSql    {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param fieldParams {@link java.lang.String} <p>The field params parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic all by field where return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<E> findDynamicAllByFieldWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams);

}
