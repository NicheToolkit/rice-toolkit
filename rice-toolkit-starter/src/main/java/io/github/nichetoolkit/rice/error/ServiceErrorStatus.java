package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <p>ServiceErrorStatus</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Getter
public enum ServiceErrorStatus implements RestStatus {
    SERVICE_REALIZATION_ERROR(10402,"the service must implement the interface of 'IdFilterService'"),
    SERVICE_ANNOTATION_ERROR(10403,"the service must use the annotation of '@RestService'"),
    SERVICE_UNKNOWN_ERROR(10404,"the service has unknown error "),
    SERVICE_UNSUPPORTED_ERROR(10405,"the service method is unsupported"),
    SERVICE_UNAUTHORIZED_ERROR(10406,"the service method is unauthorized"),
    SERVICE_UNCAUGHT_ERROR(10407,"the service method has uncaught error"),
    ;

    private final Integer status;
    private final String message;

    ServiceErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

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
