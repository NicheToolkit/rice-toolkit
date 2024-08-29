package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

public class TableIsNotExistException extends ServiceErrorException {

    public TableIsNotExistException() {
        super(TableErrorStatus.TABLE_IS_NOT_EXIST);
    }

    public TableIsNotExistException(String error) {
        super( TableErrorStatus.TABLE_IS_NOT_EXIST,error);
    }

    public TableIsNotExistException(String tablename, String error) {
        super(TableErrorStatus.TABLE_IS_NOT_EXIST, tablename, error);
    }

    @Override
    public TableIsNotExistException get() {
        return new TableIsNotExistException();
    }
}
