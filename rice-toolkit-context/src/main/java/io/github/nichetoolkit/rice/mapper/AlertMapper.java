package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>AlertMapper</code>
 * <p>The alert mapper interface.</p>
 * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AlertMapper<S,I> {

    /**
     * <code>alertById</code>
     * <p>The alert by id method.</p>
     * @param id    I <p>The id parameter is <code>I</code> type.</p>
     * @param state S <p>The state parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert by id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertById(@Param("id") I id, @Param("state") S state);

    /**
     * <code>alertDynamicById</code>
     * <p>The alert dynamic by id method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert dynamic by id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertDynamicById(@Param("tableName") String tableName, @Param("id") I id, @Param("state") S state);

    /**
     * <code>alertAll</code>
     * <p>The alert all method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param state  S <p>The state parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertAll(@Param("idList") Collection<I> idList, @Param("state") S state);

    /**
     * <code>alertDynamicAll</code>
     * <p>The alert dynamic all method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert dynamic all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer alertDynamicAll(@Param("tableName") String tableName, @Param("idList") Collection<I> idList, @Param("state") S state);

    /**
     * <code>alertAllByWhere</code>
     * <p>The alert all by where method.</p>
     * @param whereSql {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param state    S <p>The state parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertAllByWhere(@Param("whereSql") String whereSql, @Param("state") S state);

    /**
     * <code>alertDynamicAllByWhere</code>
     * <p>The alert dynamic all by where method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert dynamic all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertDynamicAllByWhere(@Param("tableName") String tableName, @Param("whereSql") String whereSql, @Param("state") S state);

    /**
     * <code>alertById</code>
     * <p>The alert by id method.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link java.lang.String} <p>The state name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert by id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.lang.Integer
     */
    Integer alertById(@Param("id") I id, @Param("state") S state, @Param("stateName") String stateName);

    /**
     * <code>alertDynamicById</code>
     * <p>The alert dynamic by id method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link java.lang.String} <p>The state name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert dynamic by id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertDynamicById(@Param("tableName") String tableName, @Param("id") I id, @Param("state") S state, @Param("stateName") String stateName);

    /**
     * <code>alertAll</code>
     * <p>The alert all method.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link java.lang.String} <p>The state name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert all return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.lang.Integer
     */
    Integer alertAll(@Param("idList") Collection<I> idList, @Param("state") S state, @Param("stateName") String stateName);

    /**
     * <code>alertDynamicAll</code>
     * <p>The alert dynamic all method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link java.lang.String} <p>The state name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert dynamic all return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer alertDynamicAll(@Param("tableName") String tableName, @Param("idList") Collection<I> idList, @Param("state") S state, @Param("stateName") String stateName);

    /**
     * <code>alertAllByWhere</code>
     * <p>The alert all by where method.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link java.lang.String} <p>The state name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertAllByWhere(@Param("whereSql") String whereSql, @Param("state") S state, @Param("stateName") String stateName);

    /**
     * <code>alertDynamicAllByWhere</code>
     * <p>The alert dynamic all by where method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link java.lang.String} <p>The state name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert dynamic all by where return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertDynamicAllByWhere(@Param("tableName") String tableName, @Param("whereSql") String whereSql, @Param("state") S state, @Param("stateName") String stateName);

}
