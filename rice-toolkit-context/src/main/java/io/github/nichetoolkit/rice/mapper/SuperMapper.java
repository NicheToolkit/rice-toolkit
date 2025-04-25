package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>SuperMapper</code>
 * <p>The super mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see io.github.nichetoolkit.rice.mapper.SaveMapper
 * @see io.github.nichetoolkit.rice.mapper.FindMapper
 * @see io.github.nichetoolkit.rice.mapper.DeleteMapper
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface SuperMapper<E extends RestId<I>, I> extends SaveMapper<E, I>, FindMapper<E, I>, DeleteMapper<I> {
    /**
     * <code>tableColumns</code>
     * <p>The table columns method.</p>
     * @return {@link java.util.List} <p>The table columns return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    List<String> tableColumns();

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
