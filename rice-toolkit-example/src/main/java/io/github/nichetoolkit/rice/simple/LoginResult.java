package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rice.RestLoginResult;

/**
 * <code>LoginResult</code>
 * <p>The type login result class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestLoginResult
 * @since Jdk1.8
 */
public class LoginResult extends RestLoginResult<LoginResult> {
    private String userId;
    private UserModel user;

    /**
     * <code>LoginResult</code>
     * Instantiates a new login result.
     */
    public LoginResult() {
    }

    /**
     * <code>LoginResult</code>
     * Instantiates a new login result.
     * @param accessToken {@link java.lang.String} <p>the access token parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LoginResult(String accessToken) {
        super(accessToken);
    }

    /**
     * <code>getUserId</code>
     * <p>the user id getter method.</p>
     * @return {@link java.lang.String} <p>the user id return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getUserId() {
        return userId;
    }

    /**
     * <code>setUserId</code>
     * <p>the user id setter method.</p>
     * @param userId {@link java.lang.String} <p>the user id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * <code>getUser</code>
     * <p>the user getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.simple.UserModel} <p>the user return object is <code>UserModel</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.UserModel
     */
    public UserModel getUser() {
        return user;
    }

    /**
     * <code>setUser</code>
     * <p>the user setter method.</p>
     * @param user {@link io.github.nichetoolkit.rice.simple.UserModel} <p>the user parameter is <code>UserModel</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.UserModel
     */
    public void setUser(UserModel user) {
        this.user = user;
    }
}
