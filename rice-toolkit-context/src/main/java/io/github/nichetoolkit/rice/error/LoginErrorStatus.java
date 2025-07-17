package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.util.I18nUtils;
import lombok.Getter;

/**
 * <code>LoginErrorStatus</code>
 * <p>The login error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk1.8
 */
@Getter
public enum LoginErrorStatus implements RestStatus {
    /**
     * <code>LOGIN_INFO_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.LoginErrorStatus} <p>The <code>LOGIN_INFO_ERROR</code> field.</p>
     */
    LOGIN_INFO_ERROR(11201, "The login info verification is error"),
    /**
     * <code>LOGIN_ACCOUNT_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.LoginErrorStatus} <p>The <code>LOGIN_ACCOUNT_ERROR</code> field.</p>
     */
    LOGIN_ACCOUNT_ERROR(11202, "The login account verification is error"),
    /**
     * <code>LOGIN_PASSWORD_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.LoginErrorStatus} <p>The <code>LOGIN_PASSWORD_ERROR</code> field.</p>
     */
    LOGIN_PASSWORD_ERROR(11203, "The login password verification is error"),
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

    @Override
    public String getMessage() {
        return I18nUtils.message(name(), this.message);
    }
}
