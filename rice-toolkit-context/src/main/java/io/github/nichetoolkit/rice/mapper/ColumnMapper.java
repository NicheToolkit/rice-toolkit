package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rest.RestField;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>ColumnMapper</code>
 * <p>The column mapper interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk17
 */
public interface ColumnMapper {
    /**
     * <code>findColumns</code>
     * <p>The find columns method.</p>
     * @return {@link java.util.List} <p>The find columns return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    List<String> findColumns();

    /**
     * <code>createIndex</code>
     * <p>The create index method.</p>
     * @param field {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestField
     * @see org.apache.ibatis.annotations.Param
     */
    void createIndex(@Param("field") RestField<?> field);

    /**
     * <code>dropIndex</code>
     * <p>The drop index method.</p>
     * @param field {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestField
     * @see org.apache.ibatis.annotations.Param
     */
    void dropIndex(@Param("field") RestField<?> field);

    /**
     * <code>addColumn</code>
     * <p>The add column method.</p>
     * @param field {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestField
     * @see org.apache.ibatis.annotations.Param
     */
    void addColumn(@Param("field") RestField<?> field);

    /**
     * <code>modifyColumn</code>
     * <p>The modify column method.</p>
     * @param field {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestField
     * @see org.apache.ibatis.annotations.Param
     */
    void modifyColumn(@Param("field") RestField<?> field);

    /**
     * <code>dropColumn</code>
     * <p>The drop column method.</p>
     * @param field {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestField
     * @see org.apache.ibatis.annotations.Param
     */
    void dropColumn(@Param("field") RestField<?> field);
}
