package io.github.nichetoolkit.rice.error.login;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.LoginErrorStatus;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <p>ServiceAccessException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class NoPermissionException extends ServiceErrorException {

    public NoPermissionException() {
        super(LoginErrorStatus.NO_PERMISSION_ACCESS);
    }

    public NoPermissionException(String service) {
        super(service, LoginErrorStatus.NO_PERMISSION_ACCESS);
    }

    public NoPermissionException(String service, String error) {
        super(service, LoginErrorStatus.NO_PERMISSION_ACCESS, error);
    }

    @Override
    public NoPermissionException get() {
        return new NoPermissionException();
    }
}
