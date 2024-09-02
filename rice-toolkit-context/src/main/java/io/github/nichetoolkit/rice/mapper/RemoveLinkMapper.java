package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>RemoveLinkMapper</code>
 * <p>The type remove link mapper interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.mapper.RemoveMapper
 * @since Jdk1.8
 */
public interface RemoveLinkMapper<I> extends RemoveMapper<I> {

    /**
     * <code>removeByLinkId</code>
     * <p>the by link id method.</p>
     * @param linkId I <p>the link id parameter is <code>I</code> type.</p>
     * @param sign   {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the by link id return object is <code>Integer</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.lang.Integer
     */
    Integer removeByLinkId(@Param("linkId") I linkId, @Param("sign") String sign);

    /**
     * <code>removeDynamicByLinkId</code>
     * <p>the dynamic by link id method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param linkId    I <p>the link id parameter is <code>I</code> type.</p>
     * @param sign      {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic by link id return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Integer
     */
    Integer removeDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") I linkId, @Param("sign") String sign);

    /**
     * <code>removeAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @param sign       {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the all by link ids return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.String
     * @see java.lang.Integer
     */
    Integer removeAllByLinkIds(@Param("linkIdList") Collection<I> linkIdList, @Param("sign") String sign);

    /**
     * <code>removeDynamicAllByLinkIds</code>
     * <p>the dynamic all by link ids method.</p>
     * @param tablename  {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @param sign       {@link java.lang.String} <p>the sign parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>the dynamic all by link ids return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    Integer removeDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<I> linkIdList, @Param("sign") String sign);

}
