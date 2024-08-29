package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

public class ServiceUnsupportedException extends ServiceErrorException {
    public ServiceUnsupportedException() {
        super(ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR);
    }

    public ServiceUnsupportedException(String error) {
        super(ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR,error);
    }

    public ServiceUnsupportedException(String service, String error) {
        super( ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR,service,error);
    }

    public ServiceUnsupportedException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR, error);
    }

    @Override
    public ServiceUnsupportedException get() {
        return new ServiceUnsupportedException();
    }
}
