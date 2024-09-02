package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <code>TableErrorStatus</code>
 * <p>The type table error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk1.8
 */
@Getter
public enum TableErrorStatus implements RestStatus {
    /**
     * <code>TABLE_UNKNOWN_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.TableErrorStatus} <p>the <code>TABLE_UNKNOWN_ERROR</code> field.</p>
     */
    TABLE_UNKNOWN_ERROR(11310,"the table has unknown error!"),
    /**
     * <code>TABLE_NAME_IS_NULL</code>
     * {@link io.github.nichetoolkit.rice.error.TableErrorStatus} <p>the <code>TABLE_NAME_IS_NULL</code> field.</p>
     */
    TABLE_NAME_IS_NULL(11311, "the table name can not be null!"),
    /**
     * <code>TABLE_NON_EXIST</code>
     * {@link io.github.nichetoolkit.rice.error.TableErrorStatus} <p>the <code>TABLE_NON_EXIST</code> field.</p>
     */
    TABLE_NON_EXIST(11312, "the table structure is not exist!"),
    /**
     * <code>TABLE_ALREADY_EXIST</code>
     * {@link io.github.nichetoolkit.rice.error.TableErrorStatus} <p>the <code>TABLE_ALREADY_EXIST</code> field.</p>
     */
    TABLE_ALREADY_EXIST(11313, "the table structure already exists!"),
    ;

    private final Integer status;
    private final String message;

    TableErrorStatus(Integer status, String message) {
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
