package io.github.nichetoolkit.rice;

/**
 * <p>RiceIdService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceIdService<M extends RiceIdModel<M, E>, E extends RiceIdEntity<E, M>, F extends RiceFilter> extends RestIdService<String, String, M, E, F> {
}
