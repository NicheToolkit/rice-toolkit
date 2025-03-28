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
     * <code>tableColumns</code>
     * <p>The table columns method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The table columns return object is <code>List</code> type.</p>
     */
    List<String> tableColumns(@Param("tablename") String tablename);

    /**
     * <code>createIndex</code>
     * <p>The create index method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param field {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  io.github.nichetoolkit.rest.RestField
     */
    void createIndex(@Param("tablename") String tablename, @Param("field") RestField<?> field);

    /**
     * <code>dropIndex</code>
     * <p>The drop index method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param field {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  io.github.nichetoolkit.rest.RestField
     */
    void dropIndex(@Param("tablename") String tablename, @Param("field") RestField<?> field);

    /**
     * <code>addColumn</code>
     * <p>The add column method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param field {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  io.github.nichetoolkit.rest.RestField
     */
    void addColumn(@Param("tablename") String tablename, @Param("field") RestField<?> field);

    /**
     * <code>modifyColumn</code>
     * <p>The modify column method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param field {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  io.github.nichetoolkit.rest.RestField
     */
    void modifyColumn(@Param("tablename") String tablename, @Param("field") RestField<?> field);

    /**
     * <code>dropColumn</code>
     * <p>The drop column method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param field {@link io.github.nichetoolkit.rest.RestField} <p>The field parameter is <code>RestField</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  io.github.nichetoolkit.rest.RestField
     */
    void dropColumn(@Param("tablename") String tablename, @Param("field") RestField<?> field);
}
