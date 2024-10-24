package io.github.nichetoolkit.rice.error.table;


import io.github.nichetoolkit.rest.error.natives.ServiceErrorException;
import io.github.nichetoolkit.rice.error.TableErrorStatus;

/**
 * <code>TablenameIsNullException</code>
 * <p>The tablename is null exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ServiceErrorException
 * @since Jdk1.8
 */
public class TablenameIsNullException extends ServiceErrorException {

    /**
     * <code>TablenameIsNullException</code>
     * <p>Instantiates a new tablename is null exception.</p>
     */
    public TablenameIsNullException() {
        super(TableErrorStatus.TABLE_NAME_IS_NULL);
    }

    /**
     * <code>TablenameIsNullException</code>
     * <p>Instantiates a new tablename is null exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
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
