package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>AlertBiFieldMapper</code>
 * <p>The type alert bi field mapper interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AlertBiFieldMapper<I> {

    /**
     * <code>alertBiFieldById</code>
     * <p>the bi field by id method.</p>
     * @param id      I <p>the id parameter is <code>I</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param biField {@link java.lang.String} <p>the bi field parameter is <code>String</code> type.</p>
     * @param key     {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the bi field by id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.lang.Integer
     */
    Integer alertBiFieldById(@Param("id") I id, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key);

    /**
     * <code>alertDynamicBiFieldById</code>
     * <p>the dynamic bi field by id method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param id        I <p>the id parameter is <code>I</code> type.</p>
     * @param field     {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param biField   {@link java.lang.String} <p>the bi field parameter is <code>String</code> type.</p>
     * @param key       {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic bi field by id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertDynamicBiFieldById(@Param("tablename") String tablename, @Param("id") I id, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key);

    /**
     * <code>alertBiFieldAll</code>
     * <p>the bi field all method.</p>
     * @param idList  {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param biField {@link java.lang.String} <p>the bi field parameter is <code>String</code> type.</p>
     * @param key     {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the bi field all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.lang.Integer
     */
    Integer alertBiFieldAll(@Param("idList") Collection<I> idList, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key);

    /**
     * <code>alertDynamicBiFieldAll</code>
     * <p>the dynamic bi field all method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param field     {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param biField   {@link java.lang.String} <p>the bi field parameter is <code>String</code> type.</p>
     * @param key       {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic bi field all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer alertDynamicBiFieldAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key);

}
