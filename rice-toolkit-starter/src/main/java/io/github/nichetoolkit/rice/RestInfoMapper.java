package io.github.nichetoolkit.rice;

/**
 * <code>RestInfoMapper</code>
 * <p>The rest info mapper interface.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestInfoEntity} <p>The generic parameter is <code>RestInfoEntity</code> type.</p>
 * @see  io.github.nichetoolkit.rice.RestInfoEntity
 * @see  io.github.nichetoolkit.rice.DefaultInfoMapper
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestInfoMapper<E extends RestInfoEntity<E, ?>> extends DefaultInfoMapper<E, String> {
}
