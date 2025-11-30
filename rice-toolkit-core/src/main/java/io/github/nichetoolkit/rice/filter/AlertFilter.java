package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;

import java.io.Serializable;

/**
 * <code>AlertFilter</code>
 * <p>The alert filter interface.</p>
 * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @since Jdk17
 */
public interface AlertFilter<S> extends Serializable {

    /**
     * <code>getState</code>
     * <p>The get state getter method.</p>
     * @return S <p>The get state return object is <code>S</code> type.</p>
     */
    S getState();

    /**
     * <code>getStateType</code>
     * <p>The get state type getter method.</p>
     * @return {@link java.lang.Class} <p>The get state type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     * @see java.lang.SuppressWarnings
     */
    @JsonIgnore
    @SuppressWarnings("unchecked")
    default Class<S> getStateType() {
        return  (Class<S>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                AlertFilter.class.getTypeParameters()[0], getClass(), AlertFilter.class));
    }

    /**
     * <code>getStateName</code>
     * <p>The get state name getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestKey} <p>The get state name return object is <code>RestKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     */
    default RestKey<String> getStateName() {
        return null;
    }

    /**
     * <code>setState</code>
     * <p>The set state setter method.</p>
     * @param status S <p>The status parameter is <code>S</code> type.</p>
     */
    void setState(S status);

}
