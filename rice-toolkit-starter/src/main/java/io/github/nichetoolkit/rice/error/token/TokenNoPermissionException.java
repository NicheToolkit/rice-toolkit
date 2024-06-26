package io.github.nichetoolkit.rice.error.token;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;

/**
 * <p>TokenNoPermissionException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TokenNoPermissionException extends TokenErrorException {

    public TokenNoPermissionException() {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION);
    }

    public TokenNoPermissionException(String error) {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION, error);
    }

    public TokenNoPermissionException(String permission, String error) {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION, permission, error);
    }

    @Override
    public TokenNoPermissionException get() {
        return new TokenNoPermissionException();
    }
}
