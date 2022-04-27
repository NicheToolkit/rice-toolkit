package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.LoginErrorStatus;

/**
 * <p>InvalidTokenException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class InvalidTokenException extends ServiceErrorException {

    public InvalidTokenException() {
        super(LoginErrorStatus.INVALID_TOKEN_PREFIX);
    }

    public InvalidTokenException(String service) {
        super(service, LoginErrorStatus.INVALID_TOKEN_PREFIX);
    }

    public InvalidTokenException(String service, String error) {
        super(service, LoginErrorStatus.INVALID_TOKEN_PREFIX, error);
    }

    @Override
    public InvalidTokenException get() {
        return new InvalidTokenException();
    }
}
