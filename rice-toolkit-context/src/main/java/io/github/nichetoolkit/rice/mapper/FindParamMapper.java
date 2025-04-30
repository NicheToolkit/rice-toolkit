package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.mybatis.load.RestParam;
import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>FindParamMapper</code>
 * <p>The find param mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface FindParamMapper<E extends RestId<I>,I> {
    /**
     * <code>findAllByIdOrParams</code>
     * <p>The find all by id or params method.</p>
     * @param id     I <p>The id parameter is <code>I</code> type.</p>
     * @param params {@link io.github.nichetoolkit.mybatis.load.RestParam} <p>The params parameter is <code>RestParam</code> type.</p>
     * @return {@link java.util.List} <p>The find all by id or params return object is <code>List</code> type.</p>
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.load.RestParam
     * @see java.util.List
     */
    List<E> findAllByIdOrParams(@Param("id") I id, @Param("params") RestParam... params);

    /**
     * <code>findDynamicAllByIdOrParams</code>
     * <p>The find dynamic all by id or params method.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param params    {@link io.github.nichetoolkit.mybatis.load.RestParam} <p>The params parameter is <code>RestParam</code> type.</p>
     * @return {@link java.util.List} <p>The find dynamic all by id or params return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see io.github.nichetoolkit.mybatis.load.RestParam
     * @see java.util.List
     */
    List<E> findDynamicAllByIdOrParams(@Param("tablename") String tablename, @Param("id") I id, @Param("params") RestParam... params);

}
