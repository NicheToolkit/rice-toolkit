package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.constant.LoginConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <code>RiceLoginProperties</code>
 * <p>The type rice login properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.login")
public class RiceLoginProperties {
    private Boolean enabled = false;
    private String[] tokenPrefixes = LoginConstants.TOKEN_PREFIXES;
    private String[] skipUrls = {};
    private String[] tokenHeaders = LoginConstants.HEADER_TOKENS;
    private Integer tokenExpiration = 1800;
    private TimeUnit tokenTimeUnit = TimeUnit.SECONDS;
    private String accessTokenHeader = LoginConstants.ACCESS_TOKEN_HEADER;
    private String accessAuthHeader = LoginConstants.ACCESS_AUTH_HEADER;

    /**
     * <code>RiceLoginProperties</code>
     * Instantiates a new rice login properties.
     */
    public RiceLoginProperties() {
    }

    /**
     * <code>getEnabled</code>
     * <p>the enabled getter method.</p>
     * @return {@link java.lang.Boolean} <p>the enabled return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * <code>setEnabled</code>
     * <p>the enabled setter method.</p>
     * @param enabled {@link java.lang.Boolean} <p>the enabled parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * <code>getTokenPrefixes</code>
     * <p>the token prefixes getter method.</p>
     * @return {@link java.util.List} <p>the token prefixes return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<String> getTokenPrefixes() {
        if (GeneralUtils.isNotEmpty(this.tokenPrefixes)) {
            return new ArrayList<>(Arrays.asList(tokenPrefixes));
        }
        return Collections.emptyList();
    }

    /**
     * <code>setTokenPrefixes</code>
     * <p>the token prefixes setter method.</p>
     * @param tokenPrefixes {@link java.lang.String} <p>the token prefixes parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setTokenPrefixes(String... tokenPrefixes) {
        this.tokenPrefixes = tokenPrefixes;
    }

    /**
     * <code>setTokenPrefixes</code>
     * <p>the token prefixes setter method.</p>
     * @param tokenPrefixes {@link java.util.Collection} <p>the token prefixes parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
    public void setTokenPrefixes(Collection<String> tokenPrefixes) {
        if (GeneralUtils.isNotEmpty(tokenPrefixes)) {
            String[] prefixArray = new String[tokenPrefixes.size()];
            tokenPrefixes.toArray(prefixArray);
            this.tokenPrefixes = prefixArray;
        }
    }

    /**
     * <code>getSkipUrls</code>
     * <p>the skip urls getter method.</p>
     * @return {@link java.util.List} <p>the skip urls return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<String> getSkipUrls() {
        if (GeneralUtils.isNotEmpty(this.skipUrls)) {
            return new ArrayList<>(Arrays.asList(skipUrls));
        }
        return Collections.emptyList();
    }

    /**
     * <code>setSkipUrls</code>
     * <p>the skip urls setter method.</p>
     * @param skipUrls {@link java.lang.String} <p>the skip urls parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setSkipUrls(String... skipUrls) {
        this.skipUrls = skipUrls;
    }

    /**
     * <code>getTokenHeaders</code>
     * <p>the token headers getter method.</p>
     * @return {@link java.util.List} <p>the token headers return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<String> getTokenHeaders() {
        if (GeneralUtils.isNotEmpty(this.tokenHeaders)) {
            return new ArrayList<>(Arrays.asList(tokenHeaders));
        }
        return Collections.emptyList();
    }

    /**
     * <code>setTokenHeaders</code>
     * <p>the token headers setter method.</p>
     * @param tokenHeaders {@link java.lang.String} <p>the token headers parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setTokenHeaders(String... tokenHeaders) {
        this.tokenHeaders = tokenHeaders;
    }

    /**
     * <code>getTokenExpiration</code>
     * <p>the token expiration getter method.</p>
     * @return {@link java.lang.Integer} <p>the token expiration return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer getTokenExpiration() {
        return tokenExpiration;
    }

    /**
     * <code>setTokenExpiration</code>
     * <p>the token expiration setter method.</p>
     * @param tokenExpiration {@link java.lang.Integer} <p>the token expiration parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public void setTokenExpiration(Integer tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    /**
     * <code>getTokenTimeUnit</code>
     * <p>the token time unit getter method.</p>
     * @return {@link java.util.concurrent.TimeUnit} <p>the token time unit return object is <code>TimeUnit</code> type.</p>
     * @see java.util.concurrent.TimeUnit
     */
    public TimeUnit getTokenTimeUnit() {
        return tokenTimeUnit;
    }

    /**
     * <code>setTokenTimeUnit</code>
     * <p>the token time unit setter method.</p>
     * @param tokenTimeUnit {@link java.util.concurrent.TimeUnit} <p>the token time unit parameter is <code>TimeUnit</code> type.</p>
     * @see java.util.concurrent.TimeUnit
     */
    public void setTokenTimeUnit(TimeUnit tokenTimeUnit) {
        this.tokenTimeUnit = tokenTimeUnit;
    }

    /**
     * <code>getAccessTokenHeader</code>
     * <p>the access token header getter method.</p>
     * @return {@link java.lang.String} <p>the access token header return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getAccessTokenHeader() {
        return accessTokenHeader;
    }

    /**
     * <code>setAccessTokenHeader</code>
     * <p>the access token header setter method.</p>
     * @param accessTokenHeader {@link java.lang.String} <p>the access token header parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setAccessTokenHeader(String accessTokenHeader) {
        this.accessTokenHeader = accessTokenHeader;
    }

    /**
     * <code>getAccessAuthHeader</code>
     * <p>the access auth header getter method.</p>
     * @return {@link java.lang.String} <p>the access auth header return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getAccessAuthHeader() {
        return accessAuthHeader;
    }

    /**
     * <code>setAccessAuthHeader</code>
     * <p>the access auth header setter method.</p>
     * @param accessAuthHeader {@link java.lang.String} <p>the access auth header parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setAccessAuthHeader(String accessAuthHeader) {
        this.accessAuthHeader = accessAuthHeader;
    }
}
