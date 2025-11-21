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
 * @since Jdk1.8
 */
public interface AlertFilter<S> extends Serializable {

    /**
     * <code>getStatus</code>
     * <p>The get status getter method.</p>
     * @return S <p>The get status return object is <code>S</code> type.</p>
     */
    S getStatus();

    /**
     * <code>getStatusType</code>
     * <p>The get status type getter method.</p>
     * @return {@link java.lang.Class} <p>The get status type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     * @see java.lang.SuppressWarnings
     */
    @JsonIgnore
    @SuppressWarnings("unchecked")
    default Class<S> getStatusType() {
        return  (Class<S>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                AlertFilter.class.getTypeParameters()[0], getClass(), AlertFilter.class));
    }

    /**
     * <code>getStatusName</code>
     * <p>The get status name getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestKey} <p>The get status name return object is <code>RestKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     */
    default RestKey<String> getStatusName() {
        return null;
    }

    /**
     * <code>setStatus</code>
     * <p>The set status setter method.</p>
     * @param status S <p>The status parameter is <code>S</code> type.</p>
     */
    void setStatus(S status);

}
