package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rest.RestField;
import io.github.nichetoolkit.rice.RestId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>SuperMapper</code>
 * <p>The super mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see io.github.nichetoolkit.rice.mapper.SaveMapper
 * @see io.github.nichetoolkit.rice.mapper.FindMapper
 * @see io.github.nichetoolkit.rice.mapper.DeleteMapper
 * @see io.github.nichetoolkit.rice.mapper.FindParamMapper
 * @see io.github.nichetoolkit.rice.mapper.ColumnMapper
 * @see java.lang.SuppressWarnings
 * @since Jdk17
 */
@SuppressWarnings("UnusedReturnValue")
public interface SuperMapper<E extends RestId<I>, I> extends SaveMapper<E, I>, FindMapper<E, I>, DeleteMapper<I>, FindParamMapper<E, I>, ColumnMapper {

}
