package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>AlertMapper</code>
 * <p>The type alert mapper interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AlertMapper<I> {

    /**
     * <code>alertById</code>
     * <p>The by id method.</p>
     * @param id  I <p>The id parameter is <code>I</code> type.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The by id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertById(@Param("id") I id, @Param("key") Integer key);

    /**
     * <code>alertDynamicById</code>
     * <p>The dynamic by id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param key       {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The dynamic by id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("key") Integer key);

    /**
     * <code>alertAll</code>
     * <p>The all method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param key    {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertAll(@Param("idList") Collection<I> idList, @Param("key") Integer key);

    /**
     * <code>alertDynamicAll</code>
     * <p>The dynamic all method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param key       {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The dynamic all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer alertDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList, @Param("key") Integer key);

}
