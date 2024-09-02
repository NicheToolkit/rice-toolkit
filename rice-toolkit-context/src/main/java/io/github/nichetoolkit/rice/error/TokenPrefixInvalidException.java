package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;

/**
 * <code>TokenPrefixInvalidException</code>
 * <p>The type token prefix invalid exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.TokenErrorException
 * @since Jdk1.8
 */
public class TokenPrefixInvalidException extends TokenErrorException {

    /**
     * <code>TokenPrefixInvalidException</code>
     * Instantiates a new token prefix invalid exception.
     */
    public TokenPrefixInvalidException() {
        super(TokenErrorStatus.TOKEN_PREFIX_INVALID);
    }

    /**
     * <code>TokenPrefixInvalidException</code>
     * Instantiates a new token prefix invalid exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenPrefixInvalidException(String error) {
        super(TokenErrorStatus.TOKEN_PREFIX_INVALID, error);
    }

    /**
     * <code>TokenPrefixInvalidException</code>
     * Instantiates a new token prefix invalid exception.
     * @param prefix {@link java.lang.String} <p>the prefix parameter is <code>String</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenPrefixInvalidException(String prefix, String error) {
        super(TokenErrorStatus.TOKEN_PREFIX_INVALID,prefix,  error);
    }

    @Override
    public TokenPrefixInvalidException get() {
        return new TokenPrefixInvalidException();
    }
}
