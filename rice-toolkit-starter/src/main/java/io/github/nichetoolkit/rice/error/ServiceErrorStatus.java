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
    SERVICE_REALIZATION_ERROR(11102,"the service must implement the interface of 'IdFilterService'"),
    SERVICE_ANNOTATION_ERROR(11103,"the service must use the annotation of '@RestService'"),
    SERVICE_UNKNOWN_ERROR(11104,"the service has unknown error "),
    SERVICE_UNSUPPORTED_ERROR(11105,"the service method is unsupported"),
    SERVICE_UNAUTHORIZED_ERROR(11106,"the service method is unauthorized"),
    SERVICE_UNCAUGHT_ERROR(11107,"the service method has uncaught error"),
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
