package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;

/**
 * <code>LoginInfoException</code>
 * <p>The type login info exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.LoginErrorException
 * @since Jdk1.8
 */
public class LoginInfoException extends LoginErrorException {
    /**
     * <code>LoginInfoException</code>
     * Instantiates a new login info exception.
     */
    public LoginInfoException() {
        super(LoginErrorStatus.LOGIN_INFO_ERROR);
    }

    /**
     * <code>LoginInfoException</code>
     * Instantiates a new login info exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LoginInfoException(String error) {
        super(LoginErrorStatus.LOGIN_INFO_ERROR,error);
    }

    /**
     * <code>LoginInfoException</code>
     * Instantiates a new login info exception.
     * @param service {@link java.lang.String} <p>the service parameter is <code>String</code> type.</p>
     * @param error   {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LoginInfoException(String service, String error) {
        super( LoginErrorStatus.LOGIN_INFO_ERROR,service, error);
    }

    @Override
    public LoginInfoException get() {
        return new LoginInfoException();
    }
}
