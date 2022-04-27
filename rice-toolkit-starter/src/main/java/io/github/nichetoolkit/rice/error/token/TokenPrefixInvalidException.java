package io.github.nichetoolkit.rice.error.token;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;

/**
 * <p>InvalidTokenException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TokenPrefixInvalidException extends TokenErrorException {

    public TokenPrefixInvalidException() {
        super(TokenErrorStatus.TOKEN_PREFIX_INVALID);
    }

    public TokenPrefixInvalidException(String service) {
        super(service, TokenErrorStatus.TOKEN_PREFIX_INVALID);
    }

    public TokenPrefixInvalidException(String service, String error) {
        super(service, TokenErrorStatus.TOKEN_PREFIX_INVALID, error);
    }

    @Override
    public TokenPrefixInvalidException get() {
        return new TokenPrefixInvalidException();
    }
}
