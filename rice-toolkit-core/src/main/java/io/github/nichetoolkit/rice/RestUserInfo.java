package io.github.nichetoolkit.rice;

/**
 * <code>RestUserInfo</code>
 * <p>The type rest user info interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfo
 * @since Jdk1.8
 */
public interface RestUserInfo<I> extends RestInfo<I> {

    /**
     * <code>getUsername</code>
     * <p>The username getter method.</p>
     * @return {@link java.lang.String} <p>The username return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getUsername();

    /**
     * <code>setUsername</code>
     * <p>The username setter method.</p>
     * @param username {@link java.lang.String} <p>The username parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    void setUsername(String username);

}
