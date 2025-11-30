package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

/**
 * <code>TableNameIsNullException</code>
 * <p>The table name is null exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk17
 */
public class TableNameIsNullException extends ServiceErrorException {

    /**
     * <code>TableNameIsNullException</code>
     * <p>Instantiates a new table name is null exception.</p>
     */
    public TableNameIsNullException() {
        super(TableErrorStatus.TABLE_NAME_IS_NULL);
    }

    /**
     * <code>TableNameIsNullException</code>
     * <p>Instantiates a new table name is null exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TableNameIsNullException(String error) {
        super(TableErrorStatus.TABLE_NAME_IS_NULL, error);
    }

    @Override
    public TableNameIsNullException get() {
        return new TableNameIsNullException();
    }
}
