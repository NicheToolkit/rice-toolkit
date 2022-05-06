package io.github.nichetoolkit.rice.error.user;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;
import io.github.nichetoolkit.rice.error.UserErrorStatus;

/**
 * <p>UserNoPermissionException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class UserNoPermissionException extends TokenErrorException {

    public UserNoPermissionException() {
        super(UserErrorStatus.USER_NO_PERMISSION);
    }

    public UserNoPermissionException(String permission) {
        super(permission, UserErrorStatus.USER_NO_PERMISSION);
    }

    public UserNoPermissionException(String permission, String error) {
        super(permission, UserErrorStatus.USER_NO_PERMISSION, error);
    }

    @Override
    public UserNoPermissionException get() {
        return new UserNoPermissionException();
    }
}
