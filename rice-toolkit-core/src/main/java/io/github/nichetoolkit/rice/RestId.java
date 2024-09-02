package io.github.nichetoolkit.rice;

/**
 * <code>RestId</code>
 * <p>The type rest id interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
public interface RestId<I> {

    /**
     * <code>getId</code>
     * <p>the id getter method.</p>
     * @return I <p>the id return object is <code>I</code> type.</p>
     */
    I getId();

    /**
     * <code>setId</code>
     * <p>the id setter method.</p>
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     */
    void setId(I id);
}
