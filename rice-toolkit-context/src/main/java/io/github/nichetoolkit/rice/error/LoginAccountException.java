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
     * <p>Instantiates a new login account exception.</p>
     */
    public LoginAccountException() {
        super(LoginErrorStatus.LOGIN_ACCOUNT_ERROR);
    }

    /**
     * <code>LoginAccountException</code>
     * <p>Instantiates a new login account exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LoginAccountException(String error) {
        super(LoginErrorStatus.LOGIN_ACCOUNT_ERROR,error);
    }

    /**
     * <code>LoginAccountException</code>
     * <p>Instantiates a new login account exception.</p>
     * @param service {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error   {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
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
