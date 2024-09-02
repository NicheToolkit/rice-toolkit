package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.mapper.InfoMapper;

/**
 * <code>DefaultInfoMapper</code>
 * <p>The type default info mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultInfoEntity} <p>the generic parameter is <code>DefaultInfoEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultInfoEntity
 * @see io.github.nichetoolkit.rice.mapper.InfoMapper
 * @since Jdk1.8
 */
public interface DefaultInfoMapper<E extends DefaultInfoEntity<E,?,I>,I> extends InfoMapper<E,I> {
}
