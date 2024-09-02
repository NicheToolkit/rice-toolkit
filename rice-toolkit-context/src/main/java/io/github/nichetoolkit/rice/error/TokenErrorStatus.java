package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <code>TokenErrorStatus</code>
 * <p>The type token error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk1.8
 */
@Getter
public enum TokenErrorStatus implements RestStatus {
    /**
     * <code>TOKEN_INVALID_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.TokenErrorStatus} <p>the <code>TOKEN_INVALID_ERROR</code> field.</p>
     */
    TOKEN_INVALID_ERROR(11500,"the token is invalid"),
    /**
     * <code>TOKEN_ACCESS_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.TokenErrorStatus} <p>the <code>TOKEN_ACCESS_ERROR</code> field.</p>
     */
    TOKEN_ACCESS_ERROR(11501,"the token access error"),
    /**
     * <code>TOKEN_NO_PERMISSION</code>
     * {@link io.github.nichetoolkit.rice.error.TokenErrorStatus} <p>the <code>TOKEN_NO_PERMISSION</code> field.</p>
     */
    TOKEN_NO_PERMISSION(11502,"the token is no permission"),
    /**
     * <code>TOKEN_PREFIX_INVALID</code>
     * {@link io.github.nichetoolkit.rice.error.TokenErrorStatus} <p>the <code>TOKEN_PREFIX_INVALID</code> field.</p>
     */
    TOKEN_PREFIX_INVALID(11503,"the token prefix is invalid"),
    ;

    private final Integer status;
    private final String message;

    TokenErrorStatus(Integer status, String message) {
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
