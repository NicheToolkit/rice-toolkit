package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

/**
 * <code>TableNonExistException</code>
 * <p>The table non exist exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class TableNonExistException extends ServiceErrorException {

    /**
     * <code>TableNonExistException</code>
     * <p>Instantiates a new table non exist exception.</p>
     */
    public TableNonExistException() {
        super(TableErrorStatus.TABLE_NON_EXIST);
    }

    /**
     * <code>TableNonExistException</code>
     * <p>Instantiates a new table non exist exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TableNonExistException(String error) {
        super( TableErrorStatus.TABLE_NON_EXIST,error);
    }

    /**
     * <code>TableNonExistException</code>
     * <p>Instantiates a new table non exist exception.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @param error     {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TableNonExistException(String tablename, String error) {
        super(TableErrorStatus.TABLE_NON_EXIST, tablename, error);
    }

    @Override
    public TableNonExistException get() {
        return new TableNonExistException();
    }
}
