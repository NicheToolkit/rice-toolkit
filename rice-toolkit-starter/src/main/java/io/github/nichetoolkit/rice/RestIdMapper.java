package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.mapper.SuperMapper;

/**
 * <p>RestIdMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestIdMapper<E extends RestIdEntity<E,?,I>,I> extends SuperMapper<E,I> {
}
