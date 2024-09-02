package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;

public class LoginInfoException extends LoginErrorException {
    public LoginInfoException() {
        super(LoginErrorStatus.LOGIN_INFO_ERROR);
    }

    public LoginInfoException(String error) {
        super(LoginErrorStatus.LOGIN_INFO_ERROR,error);
    }

    public LoginInfoException(String service, String error) {
        super( LoginErrorStatus.LOGIN_INFO_ERROR,service, error);
    }

    @Override
    public LoginInfoException get() {
        return new LoginInfoException();
    }
}