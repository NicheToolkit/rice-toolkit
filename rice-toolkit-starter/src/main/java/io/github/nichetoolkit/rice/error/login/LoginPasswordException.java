package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;
import io.github.nichetoolkit.rice.error.LoginErrorStatus;

/**
 * <p>LoginPasswordException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class LoginPasswordException extends LoginErrorException {
    public LoginPasswordException() {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR);
    }

    public LoginPasswordException(String error) {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR,error);
    }

    public LoginPasswordException(String service, String error) {
        super(service, LoginErrorStatus.LOGIN_PASSWORD_ERROR, error);
    }

    @Override
    public LoginPasswordException get() {
        return new LoginPasswordException();
    }
}
