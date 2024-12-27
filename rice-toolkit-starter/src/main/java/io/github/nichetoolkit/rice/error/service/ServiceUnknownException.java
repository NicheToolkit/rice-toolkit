package io.github.nichetoolkit.rice.error.service;

import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.ServiceErrorStatus;

/**
 * <code>ServiceUnknownException</code>
 * <p>The service unknown exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class ServiceUnknownException extends ServiceErrorException {
    /**
     * <code>ServiceUnknownException</code>
     * <p>Instantiates a new service unknown exception.</p>
     */
    public ServiceUnknownException() {
        super(ServiceErrorStatus.SERVICE_UNKNOWN_ERROR);
    }

    /**
     * <code>ServiceUnknownException</code>
     * <p>Instantiates a new service unknown exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public ServiceUnknownException(String error) {
        super(ServiceErrorStatus.SERVICE_UNKNOWN_ERROR,error);
    }

    /**
     * <code>ServiceUnknownException</code>
     * <p>Instantiates a new service unknown exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public ServiceUnknownException(String resource, String error) {
        super(ServiceErrorStatus.SERVICE_UNKNOWN_ERROR,resource, error);
    }

    /**
     * <code>ServiceUnknownException</code>
     * <p>Instantiates a new service unknown exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param service {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public ServiceUnknownException(String resource, String service, String error) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNKNOWN_ERROR, error);
    }

    /**
     * <code>ServiceUnknownException</code>
     * <p>Instantiates a new service unknown exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param service {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Throwable
     */
    public ServiceUnknownException(String resource, String service, String error, Throwable cause) {
        super(resource, service, ServiceErrorStatus.SERVICE_UNKNOWN_ERROR, error, cause);
    }

    @Override
    public ServiceUnknownException get() {
        return new ServiceUnknownException();
    }

}
