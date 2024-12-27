package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rice.RestLoginResult;

/**
 * <code>LoginResult</code>
 * <p>The login result class.</p>
 * @see  io.github.nichetoolkit.rice.RestLoginResult
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class LoginResult extends RestLoginResult<LoginResult> {
    /**
     * <code>userId</code>
     * {@link java.lang.String} <p>The <code>userId</code> field.</p>
     * @see  java.lang.String
     */
    private String userId;
    /**
     * <code>user</code>
     * {@link io.github.nichetoolkit.rice.simple.UserModel} <p>The <code>user</code> field.</p>
     * @see  io.github.nichetoolkit.rice.simple.UserModel
     */
    private UserModel user;

    /**
     * <code>LoginResult</code>
     * <p>Instantiates a new login result.</p>
     */
    public LoginResult() {
    }

    /**
     * <code>LoginResult</code>
     * <p>Instantiates a new login result.</p>
     * @param accessToken {@link java.lang.String} <p>The access token parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public LoginResult(String accessToken) {
        super(accessToken);
    }

    /**
     * <code>getUserId</code>
     * <p>The get user id getter method.</p>
     * @return  {@link java.lang.String} <p>The get user id return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public String getUserId() {
        return userId;
    }

    /**
     * <code>setUserId</code>
     * <p>The set user id setter method.</p>
     * @param userId {@link java.lang.String} <p>The user id parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * <code>getUser</code>
     * <p>The get user getter method.</p>
     * @return  {@link io.github.nichetoolkit.rice.simple.UserModel} <p>The get user return object is <code>UserModel</code> type.</p>
     * @see  io.github.nichetoolkit.rice.simple.UserModel
     */
    public UserModel getUser() {
        return user;
    }

    /**
     * <code>setUser</code>
     * <p>The set user setter method.</p>
     * @param user {@link io.github.nichetoolkit.rice.simple.UserModel} <p>The user parameter is <code>UserModel</code> type.</p>
     * @see  io.github.nichetoolkit.rice.simple.UserModel
     */
    public void setUser(UserModel user) {
        this.user = user;
    }
}
