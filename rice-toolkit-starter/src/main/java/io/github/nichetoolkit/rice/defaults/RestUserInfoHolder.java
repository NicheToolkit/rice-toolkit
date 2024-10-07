package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rice.RestUserInfo;

/**
 * <code>RestUserInfoHolder</code>
 * <p>The type rest user info holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class RestUserInfoHolder {
    /**
     * <code>REST_USER_HOLDER</code>
     * {@link java.lang.ThreadLocal} <p>The constant <code>REST_USER_HOLDER</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    private static final ThreadLocal<RestUserInfo<?>> REST_USER_HOLDER = new ThreadLocal<>();

    /**
     * <code>getUser</code>
     * <p>The user getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.RestUserInfo} <p>The user return object is <code>RestUserInfo</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestUserInfo
     */
    public static RestUserInfo<?> getUser() {
        return REST_USER_HOLDER.get();
    }

    /**
     * <code>setUser</code>
     * <p>The user setter method.</p>
     * @param user {@link io.github.nichetoolkit.rice.RestUserInfo} <p>The user parameter is <code>RestUserInfo</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestUserInfo
     */
    protected static void setUser(RestUserInfo<?> user) {
        REST_USER_HOLDER.set(user);
    }
}
