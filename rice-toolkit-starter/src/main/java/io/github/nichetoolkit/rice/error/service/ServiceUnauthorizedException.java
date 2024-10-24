package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <code>ServiceUnauthorizedException</code>
 * <p>The service unauthorized exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class ServiceUnauthorizedException extends ServiceErrorException {
    /**
     * <code>ServiceUnauthorizedException</code>
     * <p>Instantiates a new service unauthorized exception.</p>
     */
    public ServiceUnauthorizedException() {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR);
    }

    /**
     * <code>ServiceUnauthorizedException</code>
     * <p>Instantiates a new service unauthorized exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnauthorizedException(String error) {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR,error);
    }

    /**
     * <code>ServiceUnauthorizedException</code>
     * <p>Instantiates a new service unauthorized exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnauthorizedException(String resource, String error) {
        super(ServiceErrorStatus.SERVICE_UNAUTHORIZED_ERROR, resource, error);
    }

    /**
     * <code>ServiceUnauthorizedException</code>
     * <p>Instantiates a new service unauthorized exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param service  {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
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
