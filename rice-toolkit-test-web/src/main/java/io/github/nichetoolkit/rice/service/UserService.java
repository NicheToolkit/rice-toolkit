package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rice.simple.UserFilter;
import io.github.nichetoolkit.rice.simple.UserModel;

/**
 * <code>UserService</code>
 * <p>The type simple service interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see FilterService
 * @see SingleService
 * @since Jdk1.8
 */
public interface UserService extends FilterService<UserModel, UserFilter, String, String>, SingleService<UserModel, String, String> {
}
