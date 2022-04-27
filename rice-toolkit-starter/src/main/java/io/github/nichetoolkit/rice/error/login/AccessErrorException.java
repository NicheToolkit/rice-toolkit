package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.LoginErrorStatus;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <p>ServiceAccessException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class AccessErrorException extends ServiceErrorException {

    public AccessErrorException() {
        super(LoginErrorStatus.ACCESS_ERROR);
    }

    public AccessErrorException(String service) {
        super(service, LoginErrorStatus.ACCESS_ERROR);
    }

    public AccessErrorException(String service, String error) {
        super(service, LoginErrorStatus.ACCESS_ERROR, error);
    }

    @Override
    public AccessErrorException get() {
        return new AccessErrorException();
    }
}
