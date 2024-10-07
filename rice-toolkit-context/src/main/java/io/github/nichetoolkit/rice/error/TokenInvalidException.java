package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;

/**
 * <code>TokenInvalidException</code>
 * <p>The type token invalid exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.TokenErrorException
 * @since Jdk1.8
 */
public class TokenInvalidException extends TokenErrorException {

    /**
     * <code>TokenInvalidException</code>
     * <p>Instantiates a new token invalid exception.</p>
     */
    public TokenInvalidException() {
        super(TokenErrorStatus.TOKEN_INVALID_ERROR);
    }

    /**
     * <code>TokenInvalidException</code>
     * <p>Instantiates a new token invalid exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenInvalidException(String error) {
        super(TokenErrorStatus.TOKEN_INVALID_ERROR, error);
    }

    /**
     * <code>TokenInvalidException</code>
     * <p>Instantiates a new token invalid exception.</p>
     * @param token {@link java.lang.String} <p>The token parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenInvalidException(String token, String error) {
        super(TokenErrorStatus.TOKEN_INVALID_ERROR,token,  error);
    }

    @Override
    public TokenInvalidException get() {
        return new TokenInvalidException();
    }
}
