package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rice.RestUser;

/**
 * <p>UserHolder</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RiceUserHolder {
    private static final ThreadLocal<RestUser> REST_USER_HOLDER = new ThreadLocal<>();

    public static RestUser getUser() {
        return REST_USER_HOLDER.get();
    }

    protected static void setUser(RestUser user) {
        REST_USER_HOLDER.set(user);
    }
}
