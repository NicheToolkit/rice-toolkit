package io.github.nichetoolkit.rice;

/**
 * <code>RestIdMapper</code>
 * <p>The type rest id mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>the generic parameter is <code>RestIdEntity</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestIdEntity
 * @see io.github.nichetoolkit.rice.DefaultIdMapper
 * @since Jdk1.8
 */
public interface RestIdMapper<E extends RestIdEntity<E,?>> extends DefaultIdMapper<E,String> {
}
