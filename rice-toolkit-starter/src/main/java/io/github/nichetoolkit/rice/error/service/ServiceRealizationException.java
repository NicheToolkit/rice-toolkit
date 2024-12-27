package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <code>ServiceRealizationException</code>
 * <p>The service realization exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class ServiceRealizationException extends ServiceErrorException {

    /**
     * <code>ServiceRealizationException</code>
     * <p>Instantiates a new service realization exception.</p>
     */
    public ServiceRealizationException() {
        super(ServiceErrorStatus.SERVICE_REALIZATION_ERROR);
    }

    /**
     * <code>ServiceRealizationException</code>
     * <p>Instantiates a new service realization exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public ServiceRealizationException(String error) {
        super(ServiceErrorStatus.SERVICE_REALIZATION_ERROR, error);
    }

    /**
     * <code>ServiceRealizationException</code>
     * <p>Instantiates a new service realization exception.</p>
     * @param service {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public ServiceRealizationException(String service, String error) {
        super(ServiceErrorStatus.SERVICE_REALIZATION_ERROR, service, error);
    }

    @Override
    public ServiceRealizationException get() {
        return new ServiceRealizationException();
    }
}
