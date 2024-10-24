package io.github.nichetoolkit.rice.error.service;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <code>ServiceAnnotationException</code>
 * <p>The service annotation exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class ServiceAnnotationException extends ServiceErrorException {

    /**
     * <code>ServiceAnnotationException</code>
     * <p>Instantiates a new service annotation exception.</p>
     */
    public ServiceAnnotationException() {
        super(ServiceErrorStatus.SERVICE_ANNOTATION_ERROR);
    }

    /**
     * <code>ServiceAnnotationException</code>
     * <p>Instantiates a new service annotation exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceAnnotationException(String error) {
        super(ServiceErrorStatus.SERVICE_ANNOTATION_ERROR, error);
    }

    /**
     * <code>ServiceAnnotationException</code>
     * <p>Instantiates a new service annotation exception.</p>
     * @param service {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error   {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceAnnotationException(String service, String error) {
        super(ServiceErrorStatus.SERVICE_ANNOTATION_ERROR, service, error);
    }

    @Override
    public ServiceAnnotationException get() {
        return new ServiceAnnotationException();
    }
}
