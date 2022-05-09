package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <p>ServiceUncaughtException</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class ServiceUncaughtException extends ServiceErrorException {
    public ServiceUncaughtException() {
        super(ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR);
    }

    public ServiceUncaughtException(String resource, String service) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR);
    }

    public ServiceUncaughtException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR, error);
    }

    public ServiceUncaughtException(String resource, String service, String error, Throwable cause) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR, error, cause);
    }

    @Override
    public ServiceUncaughtException get() {
        return new ServiceUncaughtException();
    }

}
