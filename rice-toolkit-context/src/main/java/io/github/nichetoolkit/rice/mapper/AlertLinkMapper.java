package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>AlertLinkMapper</code>
 * <p>The alert link mapper interface.</p>
 * @param <L> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.mapper.AlertMapper
 * @since Jdk1.8
 */
public interface AlertLinkMapper<L, S, I> extends AlertMapper<S, I> {

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @param status S <p>The status parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert by link id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertByLinkId(@Param("linkId") L linkId, @Param("status") S status);

    /**
     * <code>alertDynamicByLinkId</code>
     * <p>The alert dynamic by link id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param status    S <p>The status parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert dynamic by link id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") L linkId, @Param("status") S status);

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert all by link ids return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer alertAllByLinkIds(@Param("linkIdList") Collection<L> linkIdList, @Param("status") S status);

    /**
     * <code>alertDynamicAllByLinkIds</code>
     * <p>The alert dynamic all by link ids method.</p>
     * @param tablename  {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @return {@link java.lang.Integer} <p>The alert dynamic all by link ids return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer alertDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<L> linkIdList, @Param("status") S status);

}
