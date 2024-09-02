package io.github.nichetoolkit.rice.error.user;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.UserErrorStatus;

/**
 * <code>UserInvalidException</code>
 * <p>The type user invalid exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.TokenErrorException
 * @since Jdk1.8
 */
public class UserInvalidException extends TokenErrorException {

    /**
     * <code>UserInvalidException</code>
     * Instantiates a new user invalid exception.
     */
    public UserInvalidException() {
        super(UserErrorStatus.USER_INVALID_ERROR);
    }

    /**
     * <code>UserInvalidException</code>
     * Instantiates a new user invalid exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UserInvalidException(String error) {
        super(UserErrorStatus.USER_INVALID_ERROR,error);
    }

    /**
     * <code>UserInvalidException</code>
     * Instantiates a new user invalid exception.
     * @param user  {@link java.lang.String} <p>the user parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UserInvalidException(String user, String error) {
        super(UserErrorStatus.USER_INVALID_ERROR,user,  error);
    }

    @Override
    public UserInvalidException get() {
        return new UserInvalidException();
    }
}
