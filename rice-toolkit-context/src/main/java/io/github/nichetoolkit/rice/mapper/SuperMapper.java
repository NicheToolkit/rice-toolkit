package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RestId;

/**
 * <code>SuperMapper</code>
 * <p>The super mapper interface.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestId
 * @see  io.github.nichetoolkit.rice.mapper.SaveMapper
 * @see  io.github.nichetoolkit.rice.mapper.FindMapper
 * @see  io.github.nichetoolkit.rice.mapper.DeleteMapper
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface SuperMapper<E extends RestId<I>, I> extends SaveMapper<E, I>, FindMapper<E, I>, DeleteMapper<I> {
}
