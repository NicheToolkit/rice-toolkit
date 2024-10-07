package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <code>ServiceUncaughtException</code>
 * <p>The type service uncaught exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class ServiceUncaughtException extends ServiceErrorException {
    /**
     * <code>ServiceUncaughtException</code>
     * <p>Instantiates a new service uncaught exception.</p>
     */
    public ServiceUncaughtException() {
        super(ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR);
    }

    /**
     * <code>ServiceUncaughtException</code>
     * <p>Instantiates a new service uncaught exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUncaughtException(String error) {
        super(ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR,error);
    }

    /**
     * <code>ServiceUncaughtException</code>
     * <p>Instantiates a new service uncaught exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUncaughtException(String resource, String error) {
        super(ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR,resource, error);
    }

    /**
     * <code>ServiceUncaughtException</code>
     * <p>Instantiates a new service uncaught exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param service  {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUncaughtException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR, error);
    }

    /**
     * <code>ServiceUncaughtException</code>
     * <p>Instantiates a new service uncaught exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param service  {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ServiceUncaughtException(String resource, String service, String error, Throwable cause) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR, error, cause);
    }

    @Override
    public ServiceUncaughtException get() {
        return new ServiceUncaughtException();
    }

}
