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
     * {@link io.github.nichetoolkit.rice.error.UserErrorStatus} <p>the <code>USER_INVALID_ERROR</code> field.</p>
     */
    USER_INVALID_ERROR(11510,"the user is invalid"),
    /**
     * <code>USER_ACCESS_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.UserErrorStatus} <p>the <code>USER_ACCESS_ERROR</code> field.</p>
     */
    USER_ACCESS_ERROR(11511,"the user access error"),
    /**
     * <code>USER_NO_PERMISSION</code>
     * {@link io.github.nichetoolkit.rice.error.UserErrorStatus} <p>the <code>USER_NO_PERMISSION</code> field.</p>
     */
    USER_NO_PERMISSION(11512,"the user is no permission"),
    ;

    private final Integer status;
    private final String message;

    UserErrorStatus(Integer status, String message) {
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
