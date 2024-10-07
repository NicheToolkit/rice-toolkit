package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.mapper.SuperMapper;

/**
 * <code>DefaultIdMapper</code>
 * <p>The type default id mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultIdEntity} <p>The generic parameter is <code>DefaultIdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultIdEntity
 * @see io.github.nichetoolkit.rice.mapper.SuperMapper
 * @since Jdk1.8
 */
public interface DefaultIdMapper<E extends DefaultIdEntity<E,?,I>,I> extends SuperMapper<E,I> {
}
