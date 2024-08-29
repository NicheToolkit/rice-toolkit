package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

public class TableUnknownException extends ServiceErrorException {

    public TableUnknownException() {
        super(TableErrorStatus.TABLE_UNKNOWN_ERROR);
    }

    public TableUnknownException(String error) {
        super(TableErrorStatus.TABLE_UNKNOWN_ERROR,error);
    }

    public TableUnknownException(String tablename, String error) {
        super(TableErrorStatus.TABLE_UNKNOWN_ERROR, tablename,  error);
    }

    @Override
    public TableUnknownException get() {
        return new TableUnknownException();
    }
}
