package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rice.RestUserInfo;

public class RestUserInfoHolder {
    private static final ThreadLocal<RestUserInfo<?>> REST_USER_HOLDER = new ThreadLocal<>();

    public static RestUserInfo<?> getUser() {
        return REST_USER_HOLDER.get();
    }

    protected static void setUser(RestUserInfo<?> user) {
        REST_USER_HOLDER.set(user);
    }
}
