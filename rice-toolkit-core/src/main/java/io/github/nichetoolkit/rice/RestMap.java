package io.github.nichetoolkit.rice;

import java.util.LinkedHashMap;

/**
 * <code>RestMap</code>
 * <p>The type rest map class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.LinkedHashMap
 * @since Jdk1.8
 */
public class RestMap extends LinkedHashMap<String, Object> {

    private static final String AUTH_KEY = "_AUTH_KEY_";

    /**
     * <code>setAuthValue</code>
     * <p>the auth value setter method.</p>
     * @param authValue {@link java.lang.String} <p>the auth value parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setAuthValue(String authValue) {
        this.put(AUTH_KEY, authValue);
    }

    /**
     * <code>getAuthValue</code>
     * <p>the auth value getter method.</p>
     * @return {@link java.lang.Object} <p>the auth value return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public Object getAuthValue() {
        return this.get(AUTH_KEY);
    }
}
