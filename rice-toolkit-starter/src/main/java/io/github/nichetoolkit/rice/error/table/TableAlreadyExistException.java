package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

/**
 * <code>TableAlreadyExistException</code>
 * <p>The type table already exist exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class TableAlreadyExistException extends ServiceErrorException {

    /**
     * <code>TableAlreadyExistException</code>
     * <p>Instantiates a new table already exist exception.</p>
     */
    public TableAlreadyExistException() {
        super(TableErrorStatus.TABLE_ALREADY_EXIST);
    }

    /**
     * <code>TableAlreadyExistException</code>
     * <p>Instantiates a new table already exist exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TableAlreadyExistException(String error) {
        super(TableErrorStatus.TABLE_ALREADY_EXIST, error);
    }

    /**
     * <code>TableAlreadyExistException</code>
     * <p>Instantiates a new table already exist exception.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param error     {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TableAlreadyExistException(String tablename, String error) {
        super(TableErrorStatus.TABLE_ALREADY_EXIST, tablename, error);
    }

    @Override
    public TableAlreadyExistException get() {
        return new TableAlreadyExistException();
    }
}
