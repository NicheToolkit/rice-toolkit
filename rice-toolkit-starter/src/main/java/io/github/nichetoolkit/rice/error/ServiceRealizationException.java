package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;

/**
 * <p>ServiceException</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class ServiceRealizationException extends ServiceErrorException {

    public ServiceRealizationException() {
        super(ServiceErrorStatus.SERVICE_REALIZATION_ERROR);
    }

    public ServiceRealizationException(String service) {
        super(service, ServiceErrorStatus.SERVICE_REALIZATION_ERROR);
    }

    public ServiceRealizationException(String service, String error) {
        super(service, ServiceErrorStatus.SERVICE_REALIZATION_ERROR, error);
    }

    @Override
    public ServiceRealizationException get() {
        return new ServiceRealizationException();
    }
}
