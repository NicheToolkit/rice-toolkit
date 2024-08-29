package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

public class ServiceUnauthorizedException extends ServiceErrorException {
    public ServiceUnauthorizedException() {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR);
    }

    public ServiceUnauthorizedException(String error) {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR,error);
    }

    public ServiceUnauthorizedException(String resource, String error) {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR, resource, error);
    }

    public ServiceUnauthorizedException(String resource, String service, String error) {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR, resource, service, error);
    }

    @Override
    public ServiceUnauthorizedException get() {
        return new ServiceUnauthorizedException();
    }
}
