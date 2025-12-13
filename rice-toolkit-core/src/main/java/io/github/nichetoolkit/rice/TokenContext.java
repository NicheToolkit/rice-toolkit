package io.github.nichetoolkit.rice;

import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * <code>TokenContext</code>
 * <p>The token context class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.LinkedHashMap
 * @since Jdk17
 */
public class TokenContext extends LinkedHashMap<String, Object> {

    /**
     * <code>AUTH_CODE_KEY</code>
     * {@link java.lang.String} <p>The constant <code>AUTH_CODE_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String AUTH_CODE_KEY = "_AUTH_CODE_KEY_";

    /**
     * <code>ACCESS_TOKEN_KEY</code>
     * {@link java.lang.String} <p>The constant <code>ACCESS_TOKEN_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String ACCESS_TOKEN_KEY = "_ACCESS_TOKEN_KEY_";

    /**
     * <code>REFRESH_TOKEN_KEY</code>
     * {@link java.lang.String} <p>The constant <code>REFRESH_TOKEN_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String REFRESH_TOKEN_KEY = "_REFRESH_TOKEN_KEY_";

    /**
     * <code>setAuthCode</code>
     * <p>The set auth code setter method.</p>
     * @param authCode {@link java.lang.String} <p>The auth code parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setAuthCode(String authCode) {
        this.put(AUTH_CODE_KEY, authCode);
    }

    /**
     * <code>getAuthCode</code>
     * <p>The get auth code getter method.</p>
     * @return {@link java.lang.String} <p>The get auth code return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getAuthCode() {
        Object authCode = this.get(AUTH_CODE_KEY);
        return Optional.ofNullable(authCode).map(String::valueOf).orElse("");
    }

    /**
     * <code>setAccessToken</code>
     * <p>The set access token setter method.</p>
     * @param accessToken {@link java.lang.String} <p>The access token parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setAccessToken(String accessToken) {
        this.put(ACCESS_TOKEN_KEY, accessToken);
    }

    /**
     * <code>getAccessToken</code>
     * <p>The get access token getter method.</p>
     * @return {@link java.lang.String} <p>The get access token return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getAccessToken() {
        Object accessToken = this.get(ACCESS_TOKEN_KEY);
        return Optional.ofNullable(accessToken).map(String::valueOf).orElse("");
    }


    /**
     * <code>setRefreshToken</code>
     * <p>The set refresh token setter method.</p>
     * @param refreshToken {@link java.lang.String} <p>The refresh token parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setRefreshToken(String refreshToken) {
        this.put(REFRESH_TOKEN_KEY, refreshToken);
    }

    /**
     * <code>getRefreshToken</code>
     * <p>The get refresh token getter method.</p>
     * @return {@link java.lang.String} <p>The get refresh token return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getRefreshToken() {
        Object refreshToken = this.get(REFRESH_TOKEN_KEY);
        return Optional.ofNullable(refreshToken).map(String::valueOf).orElse("");
    }
}
