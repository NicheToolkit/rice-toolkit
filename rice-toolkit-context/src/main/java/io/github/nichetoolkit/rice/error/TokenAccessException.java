package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;

public class TokenAccessException extends TokenErrorException {

    public TokenAccessException() {
        super(TokenErrorStatus.TOKEN_ACCESS_ERROR);
    }

    public TokenAccessException(String error) {
        super(TokenErrorStatus.TOKEN_ACCESS_ERROR, error);
    }

    public TokenAccessException(String service, String error) {
        super(TokenErrorStatus.TOKEN_ACCESS_ERROR,service,  error);
    }

    @Override
    public TokenAccessException get() {
        return new TokenAccessException();
    }
}
