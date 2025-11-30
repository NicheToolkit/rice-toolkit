package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;

/**
 * <code>TokenDuplicateException</code>
 * <p>The token duplicate exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.TokenErrorException
 * @since Jdk17
 */
public class TokenDuplicateException extends TokenErrorException {

    /**
     * <code>TokenDuplicateException</code>
     * <p>Instantiates a new token duplicate exception.</p>
     */
    public TokenDuplicateException() {
        super(TokenErrorStatus.TOKEN_DUPLICATE_ERROR);
    }

    /**
     * <code>TokenDuplicateException</code>
     * <p>Instantiates a new token duplicate exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenDuplicateException(String error) {
        super(TokenErrorStatus.TOKEN_DUPLICATE_ERROR, error);
    }

    /**
     * <code>TokenDuplicateException</code>
     * <p>Instantiates a new token duplicate exception.</p>
     * @param token {@link java.lang.String} <p>The token parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenDuplicateException(String token, String error) {
        super(TokenErrorStatus.TOKEN_DUPLICATE_ERROR,token,  error);
    }

    @Override
    public TokenDuplicateException get() {
        return new TokenDuplicateException();
    }
}
