package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>OperateLinkMapper</code>
 * <p>The operate link mapper interface.</p>
 * @param <L> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.mapper.OperateMapper
 * @since Jdk1.8
 */
public interface OperateLinkMapper<L,I> extends OperateMapper<I> {
    /**
     * <code>operateByLinkId</code>
     * <p>The operate by link id method.</p>
     * @param linkId  L <p>The link id parameter is <code>L</code> type.</p>
     * @param operate {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The operate by link id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer operateByLinkId(@Param("linkId") L linkId, @Param("operate") Integer operate);

    /**
     * <code>operateDynamicByLinkId</code>
     * <p>The operate dynamic by link id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param operate   {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The operate dynamic by link id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer operateDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") L linkId, @Param("operate") Integer operate);

    /**
     * <code>operateAllByLinkIds</code>
     * <p>The operate all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param operate    {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The operate all by link ids return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer operateAllByLinkIds(@Param("linkIdList") Collection<L> linkIdList, @Param("operate") Integer operate);

    /**
     * <code>operateDynamicAllByLinkIds</code>
     * <p>The operate dynamic all by link ids method.</p>
     * @param tablename  {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param operate    {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Integer} <p>The operate dynamic all by link ids return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer operateDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<L> linkIdList, @Param("operate") Integer operate);

}
