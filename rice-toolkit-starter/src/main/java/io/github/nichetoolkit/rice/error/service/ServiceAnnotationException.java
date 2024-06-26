package io.github.nichetoolkit.rice.error.service;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <p>ServiceAnnotationException</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class ServiceAnnotationException extends ServiceErrorException {

    public ServiceAnnotationException() {
        super(ServiceErrorStatus.SERVICE_ANNOTATION_ERROR);
    }

    public ServiceAnnotationException(String error) {
        super(ServiceErrorStatus.SERVICE_ANNOTATION_ERROR, error);
    }

    public ServiceAnnotationException(String service, String error) {
        super(ServiceErrorStatus.SERVICE_ANNOTATION_ERROR, service, error);
    }

    @Override
    public ServiceAnnotationException get() {
        return new ServiceAnnotationException();
    }
}
