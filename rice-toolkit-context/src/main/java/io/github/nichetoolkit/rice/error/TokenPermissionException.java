package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;

/**
 * <code>TokenPermissionException</code>
 * <p>The token permission exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.TokenErrorException
 * @since Jdk1.8
 */
public class TokenPermissionException extends TokenErrorException {

    /**
     * <code>TokenPermissionException</code>
     * <p>Instantiates a new token permission exception.</p>
     */
    public TokenPermissionException() {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION);
    }

    /**
     * <code>TokenPermissionException</code>
     * <p>Instantiates a new token permission exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TokenPermissionException(String error) {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION, error);
    }

    /**
     * <code>TokenPermissionException</code>
     * <p>Instantiates a new token permission exception.</p>
     * @param permission {@link java.lang.String} <p>The permission parameter is <code>String</code> type.</p>
     * @param error      {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
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
