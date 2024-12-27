package io.github.nichetoolkit.rice;

/**
 * <code>RestUserInfo</code>
 * <p>The rest user info interface.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.RestInfo
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestUserInfo<I> extends RestInfo<I> {

    /**
     * <code>getUsername</code>
     * <p>The get username getter method.</p>
     * @return  {@link java.lang.String} <p>The get username return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String getUsername();

    /**
     * <code>setUsername</code>
     * <p>The set username setter method.</p>
     * @param username {@link java.lang.String} <p>The username parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    void setUsername(String username);

}
