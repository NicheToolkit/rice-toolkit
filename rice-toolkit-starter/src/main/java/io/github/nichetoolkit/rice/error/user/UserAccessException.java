package io.github.nichetoolkit.rice.error.user;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.TokenErrorStatus;
import io.github.nichetoolkit.rice.error.UserErrorStatus;

public class UserAccessException extends TokenErrorException {

    public UserAccessException() {
        super(UserErrorStatus.USER_ACCESS_ERROR);
    }

    public UserAccessException(String error) {
        super(UserErrorStatus.USER_ACCESS_ERROR, error);
    }

    public UserAccessException(String service, String error) {
        super( UserErrorStatus.USER_ACCESS_ERROR,service, error);
    }

    @Override
    public UserAccessException get() {
        return new UserAccessException();
    }
}
