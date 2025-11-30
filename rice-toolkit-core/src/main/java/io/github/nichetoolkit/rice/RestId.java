package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.io.Serializable;

/**
 * <code>RestId</code>
 * <p>The rest id interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see java.lang.SuppressWarnings
 * @since Jdk17
 */
@SuppressWarnings("WeakerAccess")
public interface RestId<I> extends Serializable {

    /**
     * <code>getId</code>
     * <p>The get id getter method.</p>
     * @return I <p>The get id return object is <code>I</code> type.</p>
     */
    I getId();

    /**
     * <code>setId</code>
     * <p>The set id setter method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    void setId(I id);

    /**
     * <code>isEmpty</code>
     * <p>The is empty method.</p>
     * @return boolean <p>The is empty return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default boolean isEmpty() {
        return GeneralUtils.isEmpty(getId());
    }

    /**
     * <code>initialize</code>
     * <p>The initialize method.</p>
     */
    default void initialize() {
    }
}
