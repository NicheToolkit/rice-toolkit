package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rice.RestUserInfo;

/**
 * <p>UserHolder</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RiceUserHolder {
    private static final ThreadLocal<RestUserInfo> REST_USER_HOLDER = new ThreadLocal<>();

    public static RestUserInfo getUser() {
        return REST_USER_HOLDER.get();
    }

    protected static void setUser(RestUserInfo user) {
        REST_USER_HOLDER.set(user);
    }
}
