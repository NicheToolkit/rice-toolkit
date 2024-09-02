package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;

/**
 * <code>TokenAccessException</code>
 * <p>The type token access exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.TokenErrorException
 * @since Jdk1.8
 */
public class TokenAccessException extends TokenErrorException {

    /**
     * <code>TokenAccessException</code>
     * Instantiates a new token access exception.
     */
    public TokenAccessException() {
        super(TokenErrorStatus.TOKEN_ACCESS_ERROR);
    }

    /**
     * <code>TokenAccessException</code>
     * Instantiates a new token access exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenAccessException(String error) {
        super(TokenErrorStatus.TOKEN_ACCESS_ERROR, error);
    }

    /**
     * <code>TokenAccessException</code>
     * Instantiates a new token access exception.
     * @param service {@link java.lang.String} <p>the service parameter is <code>String</code> type.</p>
     * @param error   {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenAccessException(String service, String error) {
        super(TokenErrorStatus.TOKEN_ACCESS_ERROR,service,  error);
    }

    @Override
    public TokenAccessException get() {
        return new TokenAccessException();
    }
}
