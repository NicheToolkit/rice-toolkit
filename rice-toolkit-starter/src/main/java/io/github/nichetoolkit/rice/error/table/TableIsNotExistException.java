package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

/**
 * <p>TableIsNotExistException</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
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
