package io.github.nichetoolkit.rice;

import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * <code>TokenContext</code>
 * <p>The type token context class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.LinkedHashMap
 * @since Jdk1.8
 */
public class TokenContext extends LinkedHashMap<String, Object> {

    /**
     * <code>AUTH_KEY</code>
     * {@link java.lang.String} <p>the constant <code>AUTH_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String AUTH_KEY = "_AUTH_KEY_";

    /**
     * <code>TOKEN_KEY</code>
     * {@link java.lang.String} <p>the constant <code>TOKEN_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String TOKEN_KEY = "_TOKEN_KEY_";

    /**
     * <code>setAuth</code>
     * <p>the auth setter method.</p>
     * @param auth {@link java.lang.String} <p>the auth parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setAuth(String auth) {
        this.put(AUTH_KEY, auth);
    }

    /**
     * <code>getAuth</code>
     * <p>the auth getter method.</p>
     * @return {@link java.lang.String} <p>the auth return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getAuth() {
        Object auth = this.get(AUTH_KEY);
        return Optional.ofNullable(auth).map(String::valueOf).orElse("");
    }

    /**
     * <code>setToken</code>
     * <p>the token setter method.</p>
     * @param token {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setToken(String token) {
        this.put(TOKEN_KEY, token);
    }

    /**
     * <code>getToken</code>
     * <p>the token getter method.</p>
     * @return {@link java.lang.String} <p>the token return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getToken() {
        Object token = this.get(TOKEN_KEY);
        return Optional.ofNullable(token).map(String::valueOf).orElse("");
    }
}
