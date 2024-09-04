package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>DeleteLinkMapper</code>
 * <p>The type delete link mapper interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface DeleteLinkMapper<I> extends DeleteMapper<I> {

    /**
     * <code>deleteByLinkId</code>
     * <p>the by link id method.</p>
     * @param linkId I <p>the link id parameter is <code>I</code> type.</p>
     * @return {@link java.lang.Integer} <p>the by link id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteByLinkId(@Param("linkId") I linkId);

    /**
     * <code>deleteDynamicByLinkId</code>
     * <p>the dynamic by link id method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param linkId    I <p>the link id parameter is <code>I</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic by link id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") I linkId);

    /**
     * <code>deleteAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all by link ids return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteAllByLinkIds(@Param("linkIdList") Collection<I> linkIdList);

    /**
     * <code>deleteDynamicAllByLinkIds</code>
     * <p>the dynamic all by link ids method.</p>
     * @param tablename  {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all by link ids return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer deleteDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<I> linkIdList);

}
