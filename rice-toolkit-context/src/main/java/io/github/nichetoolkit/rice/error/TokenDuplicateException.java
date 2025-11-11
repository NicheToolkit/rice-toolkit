package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;

/**
 * <code>TokenInvalidException</code>
 * <p>The token invalid exception class.</p>
 * @see  TokenErrorException
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class TokenDuplicateException extends TokenErrorException {

    /**
     * <code>TokenInvalidException</code>
     * <p>Instantiates a new token invalid exception.</p>
     */
    public TokenDuplicateException() {
        super(TokenErrorStatus.TOKEN_DUPLICATE_ERROR);
    }

    /**
     * <code>TokenInvalidException</code>
     * <p>Instantiates a new token invalid exception.</p>
     * @param error {@link String} <p>The error parameter is <code>String</code> type.</p>
     * @see  String
     */
    public TokenDuplicateException(String error) {
        super(TokenErrorStatus.TOKEN_DUPLICATE_ERROR, error);
    }

    /**
     * <code>TokenInvalidException</code>
     * <p>Instantiates a new token invalid exception.</p>
     * @param token {@link String} <p>The token parameter is <code>String</code> type.</p>
     * @param error {@link String} <p>The error parameter is <code>String</code> type.</p>
     * @see  String
     */
    public TokenDuplicateException(String token, String error) {
        super(TokenErrorStatus.TOKEN_DUPLICATE_ERROR,token,  error);
    }

    @Override
    public TokenDuplicateException get() {
        return new TokenDuplicateException();
    }
}
