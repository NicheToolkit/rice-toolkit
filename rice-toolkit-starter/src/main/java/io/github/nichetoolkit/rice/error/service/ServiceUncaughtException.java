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
     * Instantiates a new service uncaught exception.
     */
    public ServiceUncaughtException() {
        super(ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR);
    }

    /**
     * <code>ServiceUncaughtException</code>
     * Instantiates a new service uncaught exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUncaughtException(String error) {
        super(ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR,error);
    }

    /**
     * <code>ServiceUncaughtException</code>
     * Instantiates a new service uncaught exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUncaughtException(String resource, String error) {
        super(ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR,resource, error);
    }

    /**
     * <code>ServiceUncaughtException</code>
     * Instantiates a new service uncaught exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param service  {@link java.lang.String} <p>the service parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUncaughtException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNCAUGHT_ERROR, error);
    }

    /**
     * <code>ServiceUncaughtException</code>
     * Instantiates a new service uncaught exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param service  {@link java.lang.String} <p>the service parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
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
