package io.github.nichetoolkit.rice.error.user;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.UserErrorStatus;

public class UserInvalidException extends TokenErrorException {

    public UserInvalidException() {
        super(UserErrorStatus.USER_INVALID_ERROR);
    }

    public UserInvalidException(String error) {
        super(UserErrorStatus.USER_INVALID_ERROR,error);
    }

    public UserInvalidException(String user, String error) {
        super(UserErrorStatus.USER_INVALID_ERROR,user,  error);
    }

    @Override
    public UserInvalidException get() {
        return new UserInvalidException();
    }
}
