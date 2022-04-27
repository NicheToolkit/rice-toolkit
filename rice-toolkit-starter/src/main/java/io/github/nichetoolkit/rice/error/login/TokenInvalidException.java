package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;

/**
 * <p>TokenInvalidException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TokenInvalidException extends TokenErrorException {

    public TokenInvalidException() {
        super(TokenErrorStatus.TOKEN_INVALID_ERROR);
    }

    public TokenInvalidException(String service) {
        super(service, TokenErrorStatus.TOKEN_INVALID_ERROR);
    }

    public TokenInvalidException(String service, String error) {
        super(service, TokenErrorStatus.TOKEN_INVALID_ERROR, error);
    }

    @Override
    public TokenInvalidException get() {
        return new TokenInvalidException();
    }
}
