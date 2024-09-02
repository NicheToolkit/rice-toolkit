package io.github.nichetoolkit.rice;

/**
 * <code>RestInfoService</code>
 * <p>The type rest info service class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestInfoModel} <p>the generic parameter is <code>RestInfoModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestInfoEntity} <p>the generic parameter is <code>RestInfoEntity</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.RestFilter} <p>the generic parameter is <code>RestFilter</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoModel
 * @see io.github.nichetoolkit.rice.RestInfoEntity
 * @see io.github.nichetoolkit.rice.RestFilter
 * @see io.github.nichetoolkit.rice.DefaultInfoService
 * @since Jdk1.8
 */
public abstract class RestInfoService<M extends RestInfoModel<M, E>, E extends RestInfoEntity<E, M>, F extends RestFilter> extends DefaultInfoService<String, String, M, E, F> {
}
