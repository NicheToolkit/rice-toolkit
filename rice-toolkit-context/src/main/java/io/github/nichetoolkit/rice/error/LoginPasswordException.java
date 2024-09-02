package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;

/**
 * <code>LoginPasswordException</code>
 * <p>The type login password exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.LoginErrorException
 * @since Jdk1.8
 */
public class LoginPasswordException extends LoginErrorException {
    /**
     * <code>LoginPasswordException</code>
     * Instantiates a new login password exception.
     */
    public LoginPasswordException() {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR);
    }

    /**
     * <code>LoginPasswordException</code>
     * Instantiates a new login password exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LoginPasswordException(String error) {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR, error);
    }

    /**
     * <code>LoginPasswordException</code>
     * Instantiates a new login password exception.
     * @param service {@link java.lang.String} <p>the service parameter is <code>String</code> type.</p>
     * @param error   {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LoginPasswordException(String service, String error) {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR, service, error);
    }

    @Override
    public LoginPasswordException get() {
        return new LoginPasswordException();
    }
}
