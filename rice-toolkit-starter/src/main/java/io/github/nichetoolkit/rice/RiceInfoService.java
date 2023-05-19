package io.github.nichetoolkit.rice;

/**
 * <p>RiceInfoService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceInfoService<M extends RiceInfoModel<M, E>, E extends RiceInfoEntity<E, M>, F extends RiceFilter> extends RestInfoService<String, String, M, E, F> {
}
