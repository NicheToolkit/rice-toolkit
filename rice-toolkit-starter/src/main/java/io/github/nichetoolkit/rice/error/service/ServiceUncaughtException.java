package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

public class ServiceUncaughtException extends ServiceErrorException {
    public ServiceUncaughtException() {
        super(ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR);
    }

    public ServiceUncaughtException(String error) {
        super(ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR,error);
    }

    public ServiceUncaughtException(String resource, String error) {
        super(ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR,resource, error);
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
