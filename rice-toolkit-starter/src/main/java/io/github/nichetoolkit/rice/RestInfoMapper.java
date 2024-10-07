package io.github.nichetoolkit.rice;

/**
 * <code>RestInfoMapper</code>
 * <p>The type rest info mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestInfoEntity} <p>The generic parameter is <code>RestInfoEntity</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoEntity
 * @see io.github.nichetoolkit.rice.DefaultInfoMapper
 * @since Jdk1.8
 */
public interface RestInfoMapper<E extends RestInfoEntity<E, ?>> extends DefaultInfoMapper<E, String> {
}
