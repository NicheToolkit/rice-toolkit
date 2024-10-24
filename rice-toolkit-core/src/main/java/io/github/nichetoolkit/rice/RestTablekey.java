package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * <code>RestTablekey</code>
 * <p>The rest tablekey interface.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @since Jdk1.8
 */
public interface RestTablekey<K> extends Serializable {

    /**
     * <code>getTablekey</code>
     * <p>The get tablekey getter method.</p>
     * @return K <p>The get tablekey return object is <code>K</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    default K getTablekey() {
        return null;
    }

}
