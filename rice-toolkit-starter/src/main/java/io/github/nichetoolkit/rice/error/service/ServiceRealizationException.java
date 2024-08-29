package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

public class ServiceRealizationException extends ServiceErrorException {

    public ServiceRealizationException() {
        super(ServiceErrorStatus.SERVICE_REALIZATION_ERROR);
    }

    public ServiceRealizationException(String error) {
        super(ServiceErrorStatus.SERVICE_REALIZATION_ERROR, error);
    }

    public ServiceRealizationException(String service, String error) {
        super(ServiceErrorStatus.SERVICE_REALIZATION_ERROR, service, error);
    }

    @Override
    public ServiceRealizationException get() {
        return new ServiceRealizationException();
    }
}
