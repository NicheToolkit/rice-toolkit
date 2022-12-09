package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <p>ServiceUnauthorizedException</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class ServiceUnauthorizedException extends ServiceErrorException {
    public ServiceUnauthorizedException() {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR);
    }

    public ServiceUnauthorizedException(String resource, String service) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR);
    }

    public ServiceUnauthorizedException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR, error);
    }

    @Override
    public ServiceUnauthorizedException get() {
        return new ServiceUnauthorizedException();
    }
}
