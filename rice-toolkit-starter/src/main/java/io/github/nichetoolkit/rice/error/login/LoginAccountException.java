package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;
import io.github.nichetoolkit.rice.error.LoginErrorStatus;

/**
 * <p>LoginAccountException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
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
