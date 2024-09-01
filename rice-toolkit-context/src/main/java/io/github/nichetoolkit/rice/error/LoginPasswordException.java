package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;

public class LoginPasswordException extends LoginErrorException {
    public LoginPasswordException() {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR);
    }

    public LoginPasswordException(String error) {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR, error);
    }

    public LoginPasswordException(String service, String error) {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR, service, error);
    }

    @Override
    public LoginPasswordException get() {
        return new LoginPasswordException();
    }
}
