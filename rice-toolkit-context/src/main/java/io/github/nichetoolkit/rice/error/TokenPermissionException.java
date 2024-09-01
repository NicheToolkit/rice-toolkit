package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;

public class TokenPermissionException extends TokenErrorException {

    public TokenPermissionException() {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION);
    }

    public TokenPermissionException(String error) {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION, error);
    }

    public TokenPermissionException(String permission, String error) {
        super(TokenErrorStatus.TOKEN_NO_PERMISSION, permission, error);
    }

    @Override
    public TokenPermissionException get() {
        return new TokenPermissionException();
    }
}
