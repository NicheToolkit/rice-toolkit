package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;
import io.github.nichetoolkit.rice.error.LoginErrorStatus;

/**
 * <p>LoginInfoException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class LoginInfoException extends LoginErrorException {
    public LoginInfoException() {
        super(LoginErrorStatus.LOGIN_INFO_ERROR);
    }

    public LoginInfoException(String error) {
        super(LoginErrorStatus.LOGIN_INFO_ERROR,error);
    }

    public LoginInfoException(String service, String error) {
        super(service, LoginErrorStatus.LOGIN_INFO_ERROR, error);
    }

    @Override
    public LoginInfoException get() {
        return new LoginInfoException();
    }
}
