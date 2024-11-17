package io.github.nichetoolkit.rice.defaults;

/**
 * <code>UserInfoHolder</code>
 * <p>The user info holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class UserInfoHolder {
    /**
     * <code>USER_INFO_HOLDER</code>
     * {@link java.lang.ThreadLocal} <p>The constant <code>USER_INFO_HOLDER</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    private static final ThreadLocal<RestUserInfo<?>> USER_INFO_HOLDER = new ThreadLocal<>();

    /**
     * <code>getUser</code>
     * <p>The get user getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.RestUserInfo} <p>The get user return object is <code>RestUserInfo</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestUserInfo
     */
    public static RestUserInfo<?> getUser() {
        return USER_INFO_HOLDER.get();
    }

    /**
     * <code>setUser</code>
     * <p>The set user setter method.</p>
     * @param user {@link io.github.nichetoolkit.rice.RestUserInfo} <p>The user parameter is <code>RestUserInfo</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestUserInfo
     */
    static void setUser(RestUserInfo<?> user) {
        USER_INFO_HOLDER.set(user);
    }
}
