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
     * {@link io.github.nichetoolkit.rice.error.TokenErrorStatus} <p>The <code>TOKEN_INVALID_ERROR</code> field.</p>
     */
    TOKEN_INVALID_ERROR(11500,"The access token verification is invalid. please login again"),
    /**
     * <code>TOKEN_ACCESS_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.TokenErrorStatus} <p>The <code>TOKEN_ACCESS_ERROR</code> field.</p>
     */
    TOKEN_ACCESS_ERROR(11501,"The access token verification is invalid"),
    /**
     * <code>TOKEN_NO_PERMISSION</code>
     * {@link io.github.nichetoolkit.rice.error.TokenErrorStatus} <p>The <code>TOKEN_NO_PERMISSION</code> field.</p>
     */
    TOKEN_NO_PERMISSION(11502,"The access token verification is no permission"),
    /**
     * <code>TOKEN_PREFIX_INVALID</code>
     * {@link io.github.nichetoolkit.rice.error.TokenErrorStatus} <p>The <code>TOKEN_PREFIX_INVALID</code> field.</p>
     */
    TOKEN_PREFIX_INVALID(11503,"The access token prefix is invalid"),
    ;

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see java.lang.String
     */
    private final String message;

    /**
     * <code>TokenErrorStatus</code>
     * <p>Instantiates a new token error status.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    TokenErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * <code>getName</code>
     * <p>The name getter method.</p>
     * @return {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
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
