package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>LoginErrorStatus</code>
 * <p>The type login error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @since Jdk1.8
 */
public enum LoginErrorStatus implements RestStatus {
    /**
     * <code>LOGIN_INFO_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.LoginErrorStatus} <p>the <code>LOGIN_INFO_ERROR</code> field.</p>
     */
    LOGIN_INFO_ERROR(11201,"the info of account or password  is error"),
    /**
     * <code>LOGIN_ACCOUNT_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.LoginErrorStatus} <p>the <code>LOGIN_ACCOUNT_ERROR</code> field.</p>
     */
    LOGIN_ACCOUNT_ERROR(11202,"the account is error"),
    /**
     * <code>LOGIN_PASSWORD_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.LoginErrorStatus} <p>the <code>LOGIN_PASSWORD_ERROR</code> field.</p>
     */
    LOGIN_PASSWORD_ERROR(11203,"the password is error"),
    ;

    private final Integer status;
    private final String message;

    LoginErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * <code>getName</code>
     * <p>the name getter method.</p>
     * @return {@link java.lang.String} <p>the name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getName() {
        return this.name().toLowerCase().replace("_", " ");
    }

    @Override
    public Integer getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
