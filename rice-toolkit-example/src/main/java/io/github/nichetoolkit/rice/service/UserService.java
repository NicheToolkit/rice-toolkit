package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rice.simple.UserFilter;
import io.github.nichetoolkit.rice.simple.UserModel;

/**
 * <code>UserService</code>
 * <p>The user service interface.</p>
 * @see  io.github.nichetoolkit.rice.service.FilterService
 * @see  io.github.nichetoolkit.rice.service.NameService
 * @see  io.github.nichetoolkit.rice.service.SingleService
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface UserService extends FilterService<UserModel, UserFilter, String, String>, NameService<UserModel, String, String>, SingleService<UserModel, String, String> {
}
