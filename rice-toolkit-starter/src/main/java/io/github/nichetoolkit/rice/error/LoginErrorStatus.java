package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <p>ServiceErrorStatus</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Getter
public enum LoginErrorStatus implements RestStatus {
    TOKEN_INVALID_ERROR(10500,"the access token is invalid"),
    ACCESS_ERROR(10501,"the service access has error"),
    NO_PERMISSION_ACCESS(10502,"the service is no permission to access"),
    TOKEN_PREFIX_INVALID(10503,"the token prefix is invalid")

    ;

    private final Integer status;
    private final String message;

    LoginErrorStatus(Integer status, String message) {
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
