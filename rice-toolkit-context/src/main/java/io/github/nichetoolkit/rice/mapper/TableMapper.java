package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rest.RestField;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>TableMapper</code>
 * <p>The table mapper interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface TableMapper {

    /**
     * <code>findTableColumns</code>
     * <p>The find table columns method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The find table columns return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.List
     */
    List<String> findTableColumns(@Param("tablename") String tablename);

    /**
     * <code>createTableIndex</code>
     * <p>The create table index method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param field     {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rest.RestField
     */
    void createTableIndex(@Param("tablename") String tablename, @Param("field") RestField<?> field);

    /**
     * <code>dropTableIndex</code>
     * <p>The drop table index method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param field     {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rest.RestField
     */
    void dropTableIndex(@Param("tablename") String tablename, @Param("field") RestField<?> field);

    /**
     * <code>addTableColumn</code>
     * <p>The add table column method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param field     {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rest.RestField
     */
    void addTableColumn(@Param("tablename") String tablename, @Param("field") RestField<?> field);

    /**
     * <code>modifyTableColumn</code>
     * <p>The modify table column method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param field     {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rest.RestField
     */
    void modifyTableColumn(@Param("tablename") String tablename, @Param("field") RestField<?> field);

    /**
     * <code>dropTableColumn</code>
     * <p>The drop table column method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param field     {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.rest.RestField
     */
    void dropTableColumn(@Param("tablename") String tablename, @Param("field") RestField<?> field);
}
