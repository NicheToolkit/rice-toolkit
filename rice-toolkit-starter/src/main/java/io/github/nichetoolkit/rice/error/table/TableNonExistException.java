package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

public class TableNonExistException extends ServiceErrorException {

    public TableNonExistException() {
        super(TableErrorStatus.TABLE_NON_EXIST);
    }

    public TableNonExistException(String error) {
        super( TableErrorStatus.TABLE_NON_EXIST,error);
    }

    public TableNonExistException(String tablename, String error) {
        super(TableErrorStatus.TABLE_NON_EXIST, tablename, error);
    }

    @Override
    public TableNonExistException get() {
        return new TableNonExistException();
    }
}
