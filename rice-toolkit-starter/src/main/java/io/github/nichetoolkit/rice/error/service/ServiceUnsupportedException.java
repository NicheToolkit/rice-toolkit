package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <p>ServiceUnsupportedException</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class ServiceUnsupportedException extends ServiceErrorException {
    public ServiceUnsupportedException() {
        super(ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR);
    }

    public ServiceUnsupportedException(String resource, String service) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR);
    }

    public ServiceUnsupportedException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR, error);
    }

    @Override
    public ServiceUnsupportedException get() {
        return new ServiceUnsupportedException();
    }
}
