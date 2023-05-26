package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <p>ServiceUnknownException</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class ServiceUnknownException extends ServiceErrorException {
    public ServiceUnknownException() {
        super(ServiceErrorStatus.SERVICE_UNKNOWN_ERROR);
    }

    public ServiceUnknownException(String error) {
        super(ServiceErrorStatus.SERVICE_UNKNOWN_ERROR,error);
    }

    public ServiceUnknownException(String resource, String error) {
        super(ServiceErrorStatus.SERVICE_UNKNOWN_ERROR,resource, error);
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
