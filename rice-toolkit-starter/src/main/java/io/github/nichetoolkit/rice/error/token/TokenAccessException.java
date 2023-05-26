package io.github.nichetoolkit.rice.error.token;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;

/**
 * <p>TokenAccessException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
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
