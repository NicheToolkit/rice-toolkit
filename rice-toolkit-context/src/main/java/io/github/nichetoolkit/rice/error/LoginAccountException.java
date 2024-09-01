package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;

public class LoginAccountException extends LoginErrorException {
    public LoginAccountException() {
        super(LoginErrorStatus.LOGIN_ACCOUNT_ERROR);
    }

    public LoginAccountException(String error) {
        super(LoginErrorStatus.LOGIN_ACCOUNT_ERROR,error);
    }

    public LoginAccountException(String service, String error) {
        super(LoginErrorStatus.LOGIN_ACCOUNT_ERROR, service, error);
    }

    @Override
    public LoginAccountException get() {
        return new LoginAccountException();
    }
}
