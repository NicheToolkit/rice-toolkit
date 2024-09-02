package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.LoginErrorException;

/**
 * <code>LoginAccountException</code>
 * <p>The type login account exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.LoginErrorException
 * @since Jdk1.8
 */
public class LoginAccountException extends LoginErrorException {
    /**
     * <code>LoginAccountException</code>
     * Instantiates a new login account exception.
     */
    public LoginAccountException() {
        super(LoginErrorStatus.LOGIN_ACCOUNT_ERROR);
    }

    /**
     * <code>LoginAccountException</code>
     * Instantiates a new login account exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LoginAccountException(String error) {
        super(LoginErrorStatus.LOGIN_ACCOUNT_ERROR,error);
    }

    /**
     * <code>LoginAccountException</code>
     * Instantiates a new login account exception.
     * @param service {@link java.lang.String} <p>the service parameter is <code>String</code> type.</p>
     * @param error   {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LoginAccountException(String service, String error) {
        super(LoginErrorStatus.LOGIN_ACCOUNT_ERROR, service, error);
    }

    @Override
    public LoginAccountException get() {
        return new LoginAccountException();
    }
}
