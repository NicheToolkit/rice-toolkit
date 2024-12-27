package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rice.simple.SimpleFilter;
import io.github.nichetoolkit.rice.simple.SimpleModel;

/**
 * <code>SimpleService</code>
 * <p>The simple service interface.</p>
 * @see  io.github.nichetoolkit.rice.service.FilterService
 * @see  io.github.nichetoolkit.rice.service.NameService
 * @see  io.github.nichetoolkit.rice.service.SingleService
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface SimpleService extends FilterService<SimpleModel, SimpleFilter, String, String>, NameService<SimpleModel, String, String>, SingleService<SimpleModel, String, String> {
}
