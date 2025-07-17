package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.util.I18nUtils;
import lombok.Getter;

/**
 * <code>ServiceErrorStatus</code>
 * <p>The service error status enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestStatus
 * @see  lombok.Getter
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
public enum ServiceErrorStatus implements RestStatus {
    /**
     * <code>SERVICE_REALIZATION_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>The <code>SERVICE_REALIZATION_ERROR</code> field.</p>
     */
    SERVICE_REALIZATION_ERROR(11102,"It has encountered a service implement related error"),
    /**
     * <code>SERVICE_ANNOTATION_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>The <code>SERVICE_ANNOTATION_ERROR</code> field.</p>
     */
    SERVICE_ANNOTATION_ERROR(11103,"It has encountered a service annotation related error"),
    /**
     * <code>SERVICE_UNKNOWN_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>The <code>SERVICE_UNKNOWN_ERROR</code> field.</p>
     */
    SERVICE_UNKNOWN_ERROR(11104,"It has encountered a service unknown error"),
    /**
     * <code>SERVICE_UNSUPPORTED_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>The <code>SERVICE_UNSUPPORTED_ERROR</code> field.</p>
     */
    SERVICE_UNSUPPORTED_ERROR(11105,"The service method is unsupported"),
    /**
     * <code>SERVICE_UNAUTHORIZED_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>The <code>SERVICE_UNAUTHORIZED_ERROR</code> field.</p>
     */
    SERVICE_UNAUTHORIZED_ERROR(11106,"The service method is unauthorized"),
    /**
     * <code>SERVICE_UNIMPLEMENTED_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>The <code>SERVICE_UNIMPLEMENTED_ERROR</code> field.</p>
     */
    SERVICE_UNIMPLEMENTED_ERROR(11107,"The service method is unimplemented"),
    /**
     * <code>SERVICE_UNCAUGHT_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>The <code>SERVICE_UNCAUGHT_ERROR</code> field.</p>
     */
    SERVICE_UNCAUGHT_ERROR(11108,"It has encountered a service uncaught error"),


    ;

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see  java.lang.Integer
     */
    private final Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see  java.lang.String
     */
    private final String message;

    /**
     * <code>ServiceErrorStatus</code>
     * <p>Instantiates a new service error status.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.String
     */
    ServiceErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return I18nUtils.message(name(), this.message);
    }
}
