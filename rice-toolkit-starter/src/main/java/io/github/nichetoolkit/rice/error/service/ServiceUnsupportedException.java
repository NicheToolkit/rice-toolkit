package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <code>ServiceUnsupportedException</code>
 * <p>The type service unsupported exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class ServiceUnsupportedException extends ServiceErrorException {
    /**
     * <code>ServiceUnsupportedException</code>
     * <p>Instantiates a new service unsupported exception.</p>
     */
    public ServiceUnsupportedException() {
        super(ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR);
    }

    /**
     * <code>ServiceUnsupportedException</code>
     * <p>Instantiates a new service unsupported exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnsupportedException(String error) {
        super(ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR,error);
    }

    /**
     * <code>ServiceUnsupportedException</code>
     * <p>Instantiates a new service unsupported exception.</p>
     * @param service {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error   {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnsupportedException(String service, String error) {
        super( ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR,service,error);
    }

    /**
     * <code>ServiceUnsupportedException</code>
     * <p>Instantiates a new service unsupported exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param service  {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnsupportedException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNSUPPORTED_ERROR, error);
    }

    @Override
    public ServiceUnsupportedException get() {
        return new ServiceUnsupportedException();
    }
}
