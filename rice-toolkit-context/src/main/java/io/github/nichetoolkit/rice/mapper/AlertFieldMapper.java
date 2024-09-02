package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>AlertFieldMapper</code>
 * <p>The type alert field mapper interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AlertFieldMapper<I> {

    /**
     * <code>alertFieldById</code>
     * <p>the field by id method.</p>
     * @param id    I <p>the id parameter is <code>I</code> type.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param key   {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the field by id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.lang.Integer
     */
    Integer alertFieldById(@Param("id") I id, @Param("field") String field, @Param("key") Integer key);

    /**
     * <code>alertDynamicFieldById</code>
     * <p>the dynamic field by id method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param id        I <p>the id parameter is <code>I</code> type.</p>
     * @param field     {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param key       {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic field by id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertDynamicFieldById(@Param("tablename") String tablename, @Param("id") I id, @Param("field") String field, @Param("key") Integer key);

    /**
     * <code>alertFieldAll</code>
     * <p>the field all method.</p>
     * @param idList {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param key    {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the field all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.lang.Integer
     */
    Integer alertFieldAll(@Param("idList") Collection<I> idList, @Param("field") String field, @Param("key") Integer key);

    /**
     * <code>alertDynamicFieldAll</code>
     * <p>the dynamic field all method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param field     {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param key       {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic field all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer alertDynamicFieldAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("field") String field, @Param("key") Integer key);


}
