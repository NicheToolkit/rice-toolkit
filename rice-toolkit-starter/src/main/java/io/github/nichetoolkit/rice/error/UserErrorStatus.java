package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <code>UserErrorStatus</code>
 * <p>The type user error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk1.8
 */
@Getter
public enum UserErrorStatus implements RestStatus {
    /**
     * <code>USER_INVALID_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.UserErrorStatus} <p>The <code>USER_INVALID_ERROR</code> field.</p>
     */
    USER_INVALID_ERROR(11510,"the user is invalid"),
    /**
     * <code>USER_ACCESS_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.UserErrorStatus} <p>The <code>USER_ACCESS_ERROR</code> field.</p>
     */
    USER_ACCESS_ERROR(11511,"the user access error"),
    /**
     * <code>USER_NO_PERMISSION</code>
     * {@link io.github.nichetoolkit.rice.error.UserErrorStatus} <p>The <code>USER_NO_PERMISSION</code> field.</p>
     */
    USER_NO_PERMISSION(11512,"the user is no permission"),
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
     * <code>UserErrorStatus</code>
     * <p>Instantiates a new user error status.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    UserErrorStatus(Integer status, String message) {
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
