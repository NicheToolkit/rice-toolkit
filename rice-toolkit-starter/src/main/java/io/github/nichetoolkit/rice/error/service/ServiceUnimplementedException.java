package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <p>ServiceUnimplementedException</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class ServiceUnimplementedException extends ServiceErrorException {
    public ServiceUnimplementedException() {
        super(ServiceErrorStatus.SERVICE_UNIMPLEMENTED_ERROR);
    }

    public ServiceUnimplementedException(String resource, String service) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNIMPLEMENTED_ERROR);
    }

    public ServiceUnimplementedException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNIMPLEMENTED_ERROR, error);
    }

    @Override
    public ServiceUnimplementedException get() {
        return new ServiceUnimplementedException();
    }
}
