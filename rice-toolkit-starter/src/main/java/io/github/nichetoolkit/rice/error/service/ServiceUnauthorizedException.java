package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <code>ServiceUnauthorizedException</code>
 * <p>The type service unauthorized exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class ServiceUnauthorizedException extends ServiceErrorException {
    /**
     * <code>ServiceUnauthorizedException</code>
     * Instantiates a new service unauthorized exception.
     */
    public ServiceUnauthorizedException() {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR);
    }

    /**
     * <code>ServiceUnauthorizedException</code>
     * Instantiates a new service unauthorized exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnauthorizedException(String error) {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR,error);
    }

    /**
     * <code>ServiceUnauthorizedException</code>
     * Instantiates a new service unauthorized exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnauthorizedException(String resource, String error) {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR, resource, error);
    }

    /**
     * <code>ServiceUnauthorizedException</code>
     * Instantiates a new service unauthorized exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param service  {@link java.lang.String} <p>the service parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnauthorizedException(String resource, String service, String error) {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR, resource, service, error);
    }

    @Override
    public ServiceUnauthorizedException get() {
        return new ServiceUnauthorizedException();
    }
}
