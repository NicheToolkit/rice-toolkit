package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <code>ServiceErrorStatus</code>
 * <p>The type service error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk1.8
 */
@Getter
public enum ServiceErrorStatus implements RestStatus {
    /**
     * <code>SERVICE_REALIZATION_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>the <code>SERVICE_REALIZATION_ERROR</code> field.</p>
     */
    SERVICE_REALIZATION_ERROR(11102,"the service must implement the interface of 'IdFilterService'"),
    /**
     * <code>SERVICE_ANNOTATION_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>the <code>SERVICE_ANNOTATION_ERROR</code> field.</p>
     */
    SERVICE_ANNOTATION_ERROR(11103,"the service must use the annotation of '@RestService'"),
    /**
     * <code>SERVICE_UNKNOWN_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>the <code>SERVICE_UNKNOWN_ERROR</code> field.</p>
     */
    SERVICE_UNKNOWN_ERROR(11104,"the service has unknown error "),
    /**
     * <code>SERVICE_UNSUPPORTED_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>the <code>SERVICE_UNSUPPORTED_ERROR</code> field.</p>
     */
    SERVICE_UNSUPPORTED_ERROR(11105,"the service method is unsupported"),
    /**
     * <code>SERVICE_UNAUTHORIZED_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>the <code>SERVICE_UNAUTHORIZED_ERROR</code> field.</p>
     */
    SERVICE_UNAUTHORIZED_ERROR(11106,"the service method is unauthorized"),
    /**
     * <code>SERVICE_UNIMPLEMENTED_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>the <code>SERVICE_UNIMPLEMENTED_ERROR</code> field.</p>
     */
    SERVICE_UNIMPLEMENTED_ERROR(11107,"the service method is unimplemented"),
    /**
     * <code>SERVICE_UNCAUGHT_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.ServiceErrorStatus} <p>the <code>SERVICE_UNCAUGHT_ERROR</code> field.</p>
     */
    SERVICE_UNCAUGHT_ERROR(11108,"the service method has uncaught error"),


    ;

    private final Integer status;
    private final String message;

    ServiceErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * <code>getName</code>
     * <p>the name getter method.</p>
     * @return {@link java.lang.String} <p>the name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getName() {
        return this.name().toLowerCase().replace("_", " ");
    }

    @Override
    public Integer getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
