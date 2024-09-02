package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

/**
 * <code>TablenameIsNullException</code>
 * <p>The type tablename is null exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class TablenameIsNullException extends ServiceErrorException {

    /**
     * <code>TablenameIsNullException</code>
     * Instantiates a new tablename is null exception.
     */
    public TablenameIsNullException() {
        super(TableErrorStatus.TABLE_NAME_IS_NULL);
    }

    /**
     * <code>TablenameIsNullException</code>
     * Instantiates a new tablename is null exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TablenameIsNullException(String error) {
        super(TableErrorStatus.TABLE_NAME_IS_NULL, error);
    }

    @Override
    public TablenameIsNullException get() {
        return new TablenameIsNullException();
    }
}
