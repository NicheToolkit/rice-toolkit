package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>DeleteLinkMapper</code>
 * <p>The delete link mapper interface.</p>
 * @param <L> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.mapper.DeleteMapper
 * @since Jdk1.8
 */
public interface DeleteLinkMapper<L,I> extends DeleteMapper<I> {

    /**
     * <code>deleteByLinkId</code>
     * <p>The delete by link id method.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @return {@link java.lang.Integer} <p>The delete by link id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteByLinkId(@Param("linkId") L linkId);

    /**
     * <code>deleteDynamicByLinkId</code>
     * <p>The delete dynamic by link id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @return {@link java.lang.Integer} <p>The delete dynamic by link id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") L linkId);

    /**
     * <code>deleteAllByLinkIds</code>
     * <p>The delete all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>The delete all by link ids return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer deleteAllByLinkIds(@Param("linkIdList") Collection<L> linkIdList);

    /**
     * <code>deleteDynamicAllByLinkIds</code>
     * <p>The delete dynamic all by link ids method.</p>
     * @param tablename  {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Integer} <p>The delete dynamic all by link ids return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer deleteDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<L> linkIdList);

}
