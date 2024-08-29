package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

public class TableAlreadyExistException extends ServiceErrorException {

    public TableAlreadyExistException() {
        super(TableErrorStatus.TABLE_ALREADY_EXIST);
    }

    public TableAlreadyExistException(String error) {
        super(TableErrorStatus.TABLE_ALREADY_EXIST, error);
    }

    public TableAlreadyExistException(String tablename, String error) {
        super(TableErrorStatus.TABLE_ALREADY_EXIST, tablename, error);
    }

    @Override
    public TableAlreadyExistException get() {
        return new TableAlreadyExistException();
    }
}
