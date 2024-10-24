package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <code>ServiceUnimplementedException</code>
 * <p>The service unimplemented exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class ServiceUnimplementedException extends ServiceErrorException {
    /**
     * <code>ServiceUnimplementedException</code>
     * <p>Instantiates a new service unimplemented exception.</p>
     */
    public ServiceUnimplementedException() {
        super(ServiceErrorStatus.SERVICE_UNIMPLEMENTED_ERROR);
    }

    /**
     * <code>ServiceUnimplementedException</code>
     * <p>Instantiates a new service unimplemented exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnimplementedException(String error) {
        super(ServiceErrorStatus.SERVICE_UNIMPLEMENTED_ERROR,error);
    }

    /**
     * <code>ServiceUnimplementedException</code>
     * <p>Instantiates a new service unimplemented exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnimplementedException(String resource, String error) {
        super(ServiceErrorStatus.SERVICE_UNIMPLEMENTED_ERROR,resource, error);
    }

    /**
     * <code>ServiceUnimplementedException</code>
     * <p>Instantiates a new service unimplemented exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param service  {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnimplementedException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNIMPLEMENTED_ERROR, error);
    }

    @Override
    public ServiceUnimplementedException get() {
        return new ServiceUnimplementedException();
    }
}
