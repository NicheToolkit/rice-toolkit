package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <p>ServiceErrorStatus</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Getter
public enum TokenErrorStatus implements RestStatus {
    TOKEN_INVALID_ERROR(10500,"the access token is invalid"),
    TOKEN_ACCESS_ERROR(10501,"the access token has error"),
    TOKEN_ACCESS_NO_PERMISSION(10502,"the access token is no permission to access"),
    TOKEN_PREFIX_INVALID(10503,"the access token prefix is invalid")

    ;

    private final Integer status;
    private final String message;

    TokenErrorStatus(Integer status, String message) {
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
