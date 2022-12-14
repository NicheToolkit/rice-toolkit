package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

/**
 * <p>TableUnknownException</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class TableUnknownException extends ServiceErrorException {

    public TableUnknownException() {
        super(TableErrorStatus.TABLE_UNKNOWN_ERROR);
    }

    public TableUnknownException(String tablename) {
        super(tablename, TableErrorStatus.TABLE_UNKNOWN_ERROR);
    }

    public TableUnknownException(String tablename, String error) {
        super(tablename, TableErrorStatus.TABLE_UNKNOWN_ERROR, error);
    }

    @Override
    public TableUnknownException get() {
        return new TableUnknownException();
    }
}
