package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;

/**
 * <code>LoginPasswordException</code>
 * <p>The login password exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.LoginErrorException
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class LoginPasswordException extends LoginErrorException {
    /**
     * <code>LoginPasswordException</code>
     * <p>Instantiates a new login password exception.</p>
     */
    public LoginPasswordException() {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR);
    }

    /**
     * <code>LoginPasswordException</code>
     * <p>Instantiates a new login password exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public LoginPasswordException(String error) {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR, error);
    }

    /**
     * <code>LoginPasswordException</code>
     * <p>Instantiates a new login password exception.</p>
     * @param service {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public LoginPasswordException(String service, String error) {
        super(LoginErrorStatus.LOGIN_PASSWORD_ERROR, service, error);
    }

    @Override
    public LoginPasswordException get() {
        return new LoginPasswordException();
    }
}
