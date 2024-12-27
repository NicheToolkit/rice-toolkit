package io.github.nichetoolkit.rice.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <code>RemoveLinkMapper</code>
 * <p>The remove link mapper interface.</p>
 * @param <L>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.mapper.RemoveMapper
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RemoveLinkMapper<L,I> extends RemoveMapper<I> {

    /**
     * <code>removeByLinkId</code>
     * <p>The remove by link id method.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove by link id return object is <code>Integer</code> type.</p>
     */
    Integer removeByLinkId(@Param("linkId") L linkId, @Param("logic") Object logic);

    /**
     * <code>removeDynamicByLinkId</code>
     * <p>The remove dynamic by link id method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove dynamic by link id return object is <code>Integer</code> type.</p>
     */
    Integer removeDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") L linkId, @Param("logic") Object logic);

    /**
     * <code>removeAllByLinkIds</code>
     * <p>The remove all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.util.Collection
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove all by link ids return object is <code>Integer</code> type.</p>
     */
    Integer removeAllByLinkIds(@Param("linkIdList") Collection<L> linkIdList, @Param("logic") Object logic);

    /**
     * <code>removeDynamicAllByLinkIds</code>
     * <p>The remove dynamic all by link ids method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  org.apache.ibatis.annotations.Param
     * @see  java.util.Collection
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @return  {@link java.lang.Integer} <p>The remove dynamic all by link ids return object is <code>Integer</code> type.</p>
     */
    Integer removeDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection<L> linkIdList, @Param("logic") Object logic);

}
