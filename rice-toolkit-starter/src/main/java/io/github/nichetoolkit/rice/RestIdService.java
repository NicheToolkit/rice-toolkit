package io.github.nichetoolkit.rice;

/**
 * <code>RestIdService</code>
 * <p>The rest id service class.</p>
 * @param <M>  {@link io.github.nichetoolkit.rice.RestIdModel} <p>The generic parameter is <code>RestIdModel</code> type.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestIdEntity} <p>The generic parameter is <code>RestIdEntity</code> type.</p>
 * @param <F>  {@link io.github.nichetoolkit.rice.RestFilter} <p>The generic parameter is <code>RestFilter</code> type.</p>
 * @see  io.github.nichetoolkit.rice.RestIdModel
 * @see  io.github.nichetoolkit.rice.RestIdEntity
 * @see  io.github.nichetoolkit.rice.RestFilter
 * @see  io.github.nichetoolkit.rice.DefaultIdService
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public abstract class RestIdService<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>, F extends RestFilter> extends DefaultIdService<M, E, F, String, String> {
}
