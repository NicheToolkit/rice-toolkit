package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;

/**
 * <p>ServiceUnknownException</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class ServiceUnknownException extends ServiceErrorException {
    public ServiceUnknownException() {
        super(ServiceErrorStatus.SERVICE_UNKNOWN_ERROR);
    }

    public ServiceUnknownException(String resource, String service) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNKNOWN_ERROR);
    }

    public ServiceUnknownException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNKNOWN_ERROR, error);
    }

    public ServiceUnknownException(String resource, String service, String error, Throwable cause) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNKNOWN_ERROR, error, cause);
    }

    @Override
    public ServiceUnknownException get() {
        return new ServiceUnknownException();
    }

}
