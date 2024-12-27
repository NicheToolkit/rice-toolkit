package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;

/**
 * <code>LoginInfoException</code>
 * <p>The login info exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.LoginErrorException
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class LoginInfoException extends LoginErrorException {
    /**
     * <code>LoginInfoException</code>
     * <p>Instantiates a new login info exception.</p>
     */
    public LoginInfoException() {
        super(LoginErrorStatus.LOGIN_INFO_ERROR);
    }

    /**
     * <code>LoginInfoException</code>
     * <p>Instantiates a new login info exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public LoginInfoException(String error) {
        super(LoginErrorStatus.LOGIN_INFO_ERROR,error);
    }

    /**
     * <code>LoginInfoException</code>
     * <p>Instantiates a new login info exception.</p>
     * @param service {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public LoginInfoException(String service, String error) {
        super( LoginErrorStatus.LOGIN_INFO_ERROR,service, error);
    }

    @Override
    public LoginInfoException get() {
        return new LoginInfoException();
    }
}
