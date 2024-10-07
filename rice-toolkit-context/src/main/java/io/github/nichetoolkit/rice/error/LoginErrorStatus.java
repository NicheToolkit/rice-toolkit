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
     * {@link io.github.nichetoolkit.rice.error.LoginErrorStatus} <p>The <code>LOGIN_INFO_ERROR</code> field.</p>
     */
    LOGIN_INFO_ERROR(11201, "The info verification of account or password is error"),
    /**
     * <code>LOGIN_ACCOUNT_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.LoginErrorStatus} <p>The <code>LOGIN_ACCOUNT_ERROR</code> field.</p>
     */
    LOGIN_ACCOUNT_ERROR(11202, "The info verification of account is error"),
    /**
     * <code>LOGIN_PASSWORD_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.LoginErrorStatus} <p>The <code>LOGIN_PASSWORD_ERROR</code> field.</p>
     */
    LOGIN_PASSWORD_ERROR(11203, "The info verification of password is error"),
    ;

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see java.lang.String
     */
    private final String message;

    /**
     * <code>LoginErrorStatus</code>
     * <p>Instantiates a new login error status.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    LoginErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * <code>getName</code>
     * <p>The name getter method.</p>
     * @return {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
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
