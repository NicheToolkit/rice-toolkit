package io.github.nichetoolkit.rice.error.user;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;
import io.github.nichetoolkit.rice.error.UserErrorStatus;

public class UserNoPermissionException extends TokenErrorException {

    public UserNoPermissionException() {
        super(UserErrorStatus.USER_NO_PERMISSION);
    }

    public UserNoPermissionException(String error) {
        super(UserErrorStatus.USER_NO_PERMISSION,error);
    }

    public UserNoPermissionException(String permission, String error) {
        super( UserErrorStatus.USER_NO_PERMISSION,permission, error);
    }

    @Override
    public UserNoPermissionException get() {
        return new UserNoPermissionException();
    }
}
