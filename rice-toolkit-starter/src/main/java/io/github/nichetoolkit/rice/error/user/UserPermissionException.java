package io.github.nichetoolkit.rice.error.user;

import io.github.nichetoolkit.rest.error.natives.TokenErrorException;
import io.github.nichetoolkit.rice.error.UserErrorStatus;

/**
 * <code>UserPermissionException</code>
 * <p>The type user permission exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.TokenErrorException
 * @since Jdk1.8
 */
public class UserPermissionException extends TokenErrorException {

    /**
     * <code>UserPermissionException</code>
     * Instantiates a new user permission exception.
     */
    public UserPermissionException() {
        super(UserErrorStatus.USER_NO_PERMISSION);
    }

    /**
     * <code>UserPermissionException</code>
     * Instantiates a new user permission exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UserPermissionException(String error) {
        super(UserErrorStatus.USER_NO_PERMISSION,error);
    }

    /**
     * <code>UserPermissionException</code>
     * Instantiates a new user permission exception.
     * @param permission {@link java.lang.String} <p>the permission parameter is <code>String</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UserPermissionException(String permission, String error) {
        super( UserErrorStatus.USER_NO_PERMISSION,permission, error);
    }

    @Override
    public UserPermissionException get() {
        return new UserPermissionException();
    }
}
