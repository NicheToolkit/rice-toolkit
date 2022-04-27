package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;

/**
 * <p>ServiceAccessException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TokenNoPermissionException extends TokenErrorException {

    public TokenNoPermissionException() {
        super(TokenErrorStatus.TOKEN_ACCESS_NO_PERMISSION);
    }

    public TokenNoPermissionException(String service) {
        super(service, TokenErrorStatus.TOKEN_ACCESS_NO_PERMISSION);
    }

    public TokenNoPermissionException(String service, String error) {
        super(service, TokenErrorStatus.TOKEN_ACCESS_NO_PERMISSION, error);
    }

    @Override
    public TokenNoPermissionException get() {
        return new TokenNoPermissionException();
    }
}
