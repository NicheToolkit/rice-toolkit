package io.github.nichetoolkit.rice.error.token;

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

    public TokenInvalidException(String error) {
        super(TokenErrorStatus.TOKEN_INVALID_ERROR, error);
    }

    public TokenInvalidException(String token, String error) {
        super(token, TokenErrorStatus.TOKEN_INVALID_ERROR, error);
    }

    @Override
    public TokenInvalidException get() {
        return new TokenInvalidException();
    }
}
