package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <p>UserErrorStatus</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Getter
public enum UserErrorStatus implements RestStatus {
    USER_INVALID_ERROR(11510,"the user is invalid"),
    USER_ACCESS_ERROR(11511,"the user access error"),
    USER_NO_PERMISSION(11512,"the user is no permission"),
    ;

    private final Integer status;
    private final String message;

    UserErrorStatus(Integer status, String message) {
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
