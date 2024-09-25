package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.RestId;

/**
 * <code>SuperMapper</code>
 * <p>The type super mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see io.github.nichetoolkit.rice.mapper.SaveMapper
 * @see io.github.nichetoolkit.rice.mapper.FindMapper
 * @see io.github.nichetoolkit.rice.mapper.DeleteMapper
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("UnusedReturnValue")
public interface SuperMapper<E extends RestId<I>, I> extends SaveMapper<E, I>, FindMapper<E, I>, DeleteMapper<I> {
}
