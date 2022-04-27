package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.LoginErrorStatus;

/**
 * <p>InvalidTokenException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TokenPrefixInvalidException extends ServiceErrorException {

    public TokenPrefixInvalidException() {
        super(LoginErrorStatus.TOKEN_PREFIX_INVALID);
    }

    public TokenPrefixInvalidException(String service) {
        super(service, LoginErrorStatus.TOKEN_PREFIX_INVALID);
    }

    public TokenPrefixInvalidException(String service, String error) {
        super(service, LoginErrorStatus.TOKEN_PREFIX_INVALID, error);
    }

    @Override
    public TokenPrefixInvalidException get() {
        return new TokenPrefixInvalidException();
    }
}
