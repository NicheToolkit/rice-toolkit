package io.github.nichetoolkit.rice.error.user;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.UserErrorStatus;

public class UserPermissionException extends TokenErrorException {

    public UserPermissionException() {
        super(UserErrorStatus.USER_NO_PERMISSION);
    }

    public UserPermissionException(String error) {
        super(UserErrorStatus.USER_NO_PERMISSION,error);
    }

    public UserPermissionException(String permission, String error) {
        super( UserErrorStatus.USER_NO_PERMISSION,permission, error);
    }

    @Override
    public UserPermissionException get() {
        return new UserPermissionException();
    }
}
