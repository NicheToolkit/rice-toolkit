package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rice.simple.SimpleFilter;
import io.github.nichetoolkit.rice.simple.SimpleModel;

/**
 * <code>SimpleService</code>
 * <p>The type simple service interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.service.FilterService
 * @see SingleService
 * @since Jdk1.8
 */
public interface SimpleService extends FilterService<String, String, SimpleModel, SimpleFilter>, SingleService<String, String, SimpleModel> {
}
