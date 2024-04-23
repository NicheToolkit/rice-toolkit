package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <p>TokenErrorStatus</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Getter
public enum TokenErrorStatus implements RestStatus {
    TOKEN_INVALID_ERROR(11500,"the token is invalid"),
    TOKEN_ACCESS_ERROR(11501,"the token access error"),
    TOKEN_NO_PERMISSION(11502,"the token is no permission"),
    TOKEN_PREFIX_INVALID(11503,"the token prefix is invalid"),
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
