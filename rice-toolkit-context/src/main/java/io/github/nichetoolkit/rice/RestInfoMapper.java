package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.mapper.InfoMapper;

/**
 * <p>RestInfoMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestInfoMapper<E extends RestInfoEntity<E,?,I>,I> extends InfoMapper<E,I> {
}
