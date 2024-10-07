package io.github.nichetoolkit.rice;

import java.io.Serializable;

/**
 * <code>RestId</code>
 * <p>The type rest id interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
public interface RestId<I> extends Serializable {

    /**
     * <code>getId</code>
     * <p>The id getter method.</p>
     * @return I <p>The id return object is <code>I</code> type.</p>
     */
    I getId();

    /**
     * <code>setId</code>
     * <p>The id setter method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    void setId(I id);
}
