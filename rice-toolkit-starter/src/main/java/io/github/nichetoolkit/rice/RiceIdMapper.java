package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.mapper.SuperMapper;

/**
 * <p>RestMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceIdMapper<E extends RiceIdEntity<?,?>> extends SuperMapper<E,String> {
}
