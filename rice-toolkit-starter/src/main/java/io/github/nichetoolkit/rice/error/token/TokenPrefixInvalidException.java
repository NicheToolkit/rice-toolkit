package io.github.nichetoolkit.rice.error.token;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;

/**
 * <p>TokenPrefixInvalidException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TokenPrefixInvalidException extends TokenErrorException {

    public TokenPrefixInvalidException() {
        super(TokenErrorStatus.TOKEN_PREFIX_INVALID);
    }

    public TokenPrefixInvalidException(String prefix) {
        super(prefix, TokenErrorStatus.TOKEN_PREFIX_INVALID);
    }

    public TokenPrefixInvalidException(String prefix, String error) {
        super(prefix, TokenErrorStatus.TOKEN_PREFIX_INVALID, error);
    }

    @Override
    public TokenPrefixInvalidException get() {
        return new TokenPrefixInvalidException();
    }
}
