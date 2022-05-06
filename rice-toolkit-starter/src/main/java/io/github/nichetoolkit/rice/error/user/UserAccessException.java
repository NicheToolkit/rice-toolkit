package io.github.nichetoolkit.rice.error.user;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;
import io.github.nichetoolkit.rice.error.UserErrorStatus;

/**
 * <p>UserAccessException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class UserAccessException extends TokenErrorException {

    public UserAccessException() {
        super(UserErrorStatus.USER_ACCESS_ERROR);
    }

    public UserAccessException(String service) {
        super(service, UserErrorStatus.USER_ACCESS_ERROR);
    }

    public UserAccessException(String service, String error) {
        super(service, UserErrorStatus.USER_ACCESS_ERROR, error);
    }

    @Override
    public UserAccessException get() {
        return new UserAccessException();
    }
}
