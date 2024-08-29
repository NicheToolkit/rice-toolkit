package io.github.nichetoolkit.rice.error.token;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;

public class TokenInvalidException extends TokenErrorException {

    public TokenInvalidException() {
        super(TokenErrorStatus.TOKEN_INVALID_ERROR);
    }

    public TokenInvalidException(String error) {
        super(TokenErrorStatus.TOKEN_INVALID_ERROR, error);
    }

    public TokenInvalidException(String token, String error) {
        super(TokenErrorStatus.TOKEN_INVALID_ERROR,token,  error);
    }

    @Override
    public TokenInvalidException get() {
        return new TokenInvalidException();
    }
}
