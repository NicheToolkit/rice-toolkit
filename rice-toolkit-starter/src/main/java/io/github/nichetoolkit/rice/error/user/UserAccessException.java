package io.github.nichetoolkit.rice.error.user;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.UserErrorStatus;

/**
 * <code>UserAccessException</code>
 * <p>The type user access exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.TokenErrorException
 * @since Jdk1.8
 */
public class UserAccessException extends TokenErrorException {

    /**
     * <code>UserAccessException</code>
     * Instantiates a new user access exception.
     */
    public UserAccessException() {
        super(UserErrorStatus.USER_ACCESS_ERROR);
    }

    /**
     * <code>UserAccessException</code>
     * Instantiates a new user access exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UserAccessException(String error) {
        super(UserErrorStatus.USER_ACCESS_ERROR, error);
    }

    /**
     * <code>UserAccessException</code>
     * Instantiates a new user access exception.
     * @param service {@link java.lang.String} <p>the service parameter is <code>String</code> type.</p>
     * @param error   {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UserAccessException(String service, String error) {
        super( UserErrorStatus.USER_ACCESS_ERROR,service, error);
    }

    @Override
    public UserAccessException get() {
        return new UserAccessException();
    }
}
