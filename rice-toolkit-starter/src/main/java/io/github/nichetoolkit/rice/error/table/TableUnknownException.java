package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

/**
 * <code>TableUnknownException</code>
 * <p>The type table unknown exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class TableUnknownException extends ServiceErrorException {

    /**
     * <code>TableUnknownException</code>
     * Instantiates a new table unknown exception.
     */
    public TableUnknownException() {
        super(TableErrorStatus.TABLE_UNKNOWN_ERROR);
    }

    /**
     * <code>TableUnknownException</code>
     * Instantiates a new table unknown exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TableUnknownException(String error) {
        super(TableErrorStatus.TABLE_UNKNOWN_ERROR,error);
    }

    /**
     * <code>TableUnknownException</code>
     * Instantiates a new table unknown exception.
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param error     {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TableUnknownException(String tablename, String error) {
        super(TableErrorStatus.TABLE_UNKNOWN_ERROR, tablename,  error);
    }

    @Override
    public TableUnknownException get() {
        return new TableUnknownException();
    }
}
