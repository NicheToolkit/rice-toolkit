package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

public class TablenameIsNullException extends ServiceErrorException {

    public TablenameIsNullException() {
        super(TableErrorStatus.TABLE_NAME_IS_NULL);
    }

    public TablenameIsNullException(String error) {
        super(TableErrorStatus.TABLE_NAME_IS_NULL, error);
    }

    @Override
    public TablenameIsNullException get() {
        return new TablenameIsNullException();
    }
}
