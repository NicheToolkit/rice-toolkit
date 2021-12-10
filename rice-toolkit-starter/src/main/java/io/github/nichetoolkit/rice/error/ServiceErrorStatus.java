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
    SERVICE_REALIZATION_ERROR(10402,"服务必须实现IdFilterService接口"),
    SERVICE_ANNOTATION_ERROR(10403,"服务必须使用@RestService注解"),
    SERVICE_UNKNOWN_ERROR(10404,"服务未知"),
    SERVICE_UNSUPPORTED_ERROR(10405,"服务方法不支持")
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
