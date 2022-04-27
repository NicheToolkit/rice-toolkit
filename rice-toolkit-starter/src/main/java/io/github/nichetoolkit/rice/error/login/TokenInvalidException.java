package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.LoginErrorStatus;

/**
 * <p>TokenInvalidException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TokenInvalidException extends ServiceErrorException {

    public TokenInvalidException() {
        super(LoginErrorStatus.TOKEN_INVALID_ERROR);
    }

    public TokenInvalidException(String service) {
        super(service, LoginErrorStatus.TOKEN_INVALID_ERROR);
    }

    public TokenInvalidException(String service, String error) {
        super(service, LoginErrorStatus.TOKEN_INVALID_ERROR, error);
    }

    @Override
    public TokenInvalidException get() {
        return new TokenInvalidException();
    }
}
