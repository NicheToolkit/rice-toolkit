package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>LoginErrorStatus</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum LoginErrorStatus implements RestStatus {
    LOGIN_INFO_ERROR(11201,"the info of account or password  is error"),
    LOGIN_ACCOUNT_ERROR(11202,"the account is error"),
    LOGIN_PASSWORD_ERROR(11203,"the password is error"),
    ;

    private final Integer status;
    private final String message;

    LoginErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

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
