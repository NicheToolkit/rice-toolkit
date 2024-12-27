package io.github.nichetoolkit.rice;

/**
 * <code>RestInfoService</code>
 * <p>The rest info service class.</p>
 * @param <M>  {@link io.github.nichetoolkit.rice.RestInfoModel} <p>The generic parameter is <code>RestInfoModel</code> type.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestInfoEntity} <p>The generic parameter is <code>RestInfoEntity</code> type.</p>
 * @param <F>  {@link io.github.nichetoolkit.rice.RestFilter} <p>The generic parameter is <code>RestFilter</code> type.</p>
 * @see  io.github.nichetoolkit.rice.RestInfoModel
 * @see  io.github.nichetoolkit.rice.RestInfoEntity
 * @see  io.github.nichetoolkit.rice.RestFilter
 * @see  io.github.nichetoolkit.rice.DefaultInfoService
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public abstract class RestInfoService<M extends RestInfoModel<M, E>, E extends RestInfoEntity<E, M>, F extends RestFilter> extends DefaultInfoService<M, E, F, String, String> {
}
