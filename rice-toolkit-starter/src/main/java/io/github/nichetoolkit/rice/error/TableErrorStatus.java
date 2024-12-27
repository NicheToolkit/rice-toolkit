package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <code>TableErrorStatus</code>
 * <p>The table error status enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestStatus
 * @see  lombok.Getter
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
public enum TableErrorStatus implements RestStatus {
    /**
     * <code>TABLE_UNKNOWN_ERROR</code>
     * {@link io.github.nichetoolkit.rice.error.TableErrorStatus} <p>The <code>TABLE_UNKNOWN_ERROR</code> field.</p>
     */
    TABLE_UNKNOWN_ERROR(11310,"the table has unknown error!"),
    /**
     * <code>TABLE_NAME_IS_NULL</code>
     * {@link io.github.nichetoolkit.rice.error.TableErrorStatus} <p>The <code>TABLE_NAME_IS_NULL</code> field.</p>
     */
    TABLE_NAME_IS_NULL(11311, "the table name can not be null!"),
    /**
     * <code>TABLE_NON_EXIST</code>
     * {@link io.github.nichetoolkit.rice.error.TableErrorStatus} <p>The <code>TABLE_NON_EXIST</code> field.</p>
     */
    TABLE_NON_EXIST(11312, "the table structure is not exist!"),
    /**
     * <code>TABLE_ALREADY_EXIST</code>
     * {@link io.github.nichetoolkit.rice.error.TableErrorStatus} <p>The <code>TABLE_ALREADY_EXIST</code> field.</p>
     */
    TABLE_ALREADY_EXIST(11313, "the table structure already exists!"),
    ;

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see  java.lang.Integer
     */
    private final Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see  java.lang.String
     */
    private final String message;

    /**
     * <code>TableErrorStatus</code>
     * <p>Instantiates a new table error status.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.String
     */
    TableErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * <code>getName</code>
     * <p>The get name getter method.</p>
     * @return  {@link java.lang.String} <p>The get name return object is <code>String</code> type.</p>
     * @see  java.lang.String
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
