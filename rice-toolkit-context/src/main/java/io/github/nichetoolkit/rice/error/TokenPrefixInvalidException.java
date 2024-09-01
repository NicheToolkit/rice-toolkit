package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;

public class TokenPrefixInvalidException extends TokenErrorException {

    public TokenPrefixInvalidException() {
        super(TokenErrorStatus.TOKEN_PREFIX_INVALID);
    }

    public TokenPrefixInvalidException(String error) {
        super(TokenErrorStatus.TOKEN_PREFIX_INVALID, error);
    }

    public TokenPrefixInvalidException(String prefix, String error) {
        super(TokenErrorStatus.TOKEN_PREFIX_INVALID,prefix,  error);
    }

    @Override
    public TokenPrefixInvalidException get() {
        return new TokenPrefixInvalidException();
    }
}
