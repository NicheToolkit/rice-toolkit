package io.github.nichetoolkit.rice;

import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * <code>TokenContext</code>
 * <p>The token context class.</p>
 * @see  java.util.LinkedHashMap
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class TokenContext extends LinkedHashMap<String, Object> {

    /**
     * <code>AUTH_KEY</code>
     * {@link java.lang.String} <p>The constant <code>AUTH_KEY</code> field.</p>
     * @see  java.lang.String
     */
    private static final String AUTH_KEY = "_AUTH_KEY_";

    /**
     * <code>TOKEN_KEY</code>
     * {@link java.lang.String} <p>The constant <code>TOKEN_KEY</code> field.</p>
     * @see  java.lang.String
     */
    private static final String TOKEN_KEY = "_TOKEN_KEY_";

    /**
     * <code>setAuth</code>
     * <p>The set auth setter method.</p>
     * @param auth {@link java.lang.String} <p>The auth parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void setAuth(String auth) {
        this.put(AUTH_KEY, auth);
    }

    /**
     * <code>getAuth</code>
     * <p>The get auth getter method.</p>
     * @return  {@link java.lang.String} <p>The get auth return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public String getAuth() {
        Object auth = this.get(AUTH_KEY);
        return Optional.ofNullable(auth).map(String::valueOf).orElse("");
    }

    /**
     * <code>setToken</code>
     * <p>The set token setter method.</p>
     * @param token {@link java.lang.String} <p>The token parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void setToken(String token) {
        this.put(TOKEN_KEY, token);
    }

    /**
     * <code>getToken</code>
     * <p>The get token getter method.</p>
     * @return  {@link java.lang.String} <p>The get token return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public String getToken() {
        Object token = this.get(TOKEN_KEY);
        return Optional.ofNullable(token).map(String::valueOf).orElse("");
    }
}
