package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;

/**
 * <p>ServiceAccessException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TokenAccessErrorException extends TokenErrorException {

    public TokenAccessErrorException() {
        super(TokenErrorStatus.TOKEN_ACCESS_ERROR);
    }

    public TokenAccessErrorException(String service) {
        super(service, TokenErrorStatus.TOKEN_ACCESS_ERROR);
    }

    public TokenAccessErrorException(String service, String error) {
        super(service, TokenErrorStatus.TOKEN_ACCESS_ERROR, error);
    }

    @Override
    public TokenAccessErrorException get() {
        return new TokenAccessErrorException();
    }
}
