package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;

/**
 * <code>TokenPermissionException</code>
 * <p>The type token permission exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.TokenErrorException
 * @since Jdk1.8
 */
public class TokenPermissionException extends TokenErrorException {

    /**
     * <code>TokenPermissionException</code>
     * Instantiates a new token permission exception.
     */
    public TokenPermissionException() {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION);
    }

    /**
     * <code>TokenPermissionException</code>
     * Instantiates a new token permission exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenPermissionException(String error) {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION, error);
    }

    /**
     * <code>TokenPermissionException</code>
     * Instantiates a new token permission exception.
     * @param permission {@link java.lang.String} <p>the permission parameter is <code>String</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenPermissionException(String permission, String error) {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION, permission, error);
    }

    @Override
    public TokenPermissionException get() {
        return new TokenPermissionException();
    }
}
