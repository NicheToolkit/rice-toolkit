package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.constant.LoginConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <code>RiceLoginProperties</code>
 * <p>The rice login properties class.</p>
 * @see  lombok.Getter
 * @see  lombok.Setter
 * @see  org.springframework.stereotype.Component
 * @see  org.springframework.boot.context.properties.ConfigurationProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.login")
public class RiceLoginProperties {
    /**
     * <code>enabled</code>
     * {@link java.lang.Boolean} <p>The <code>enabled</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean enabled = false;
    /**
     * <code>tokenPrefixes</code>
     * {@link java.lang.String} <p>The <code>tokenPrefixes</code> field.</p>
     * @see  java.lang.String
     */
    private String[] tokenPrefixes = LoginConstants.TOKEN_PREFIXES;
    /**
     * <code>skipUrls</code>
     * {@link java.lang.String} <p>The <code>skipUrls</code> field.</p>
     * @see  java.lang.String
     */
    private String[] skipUrls = {};
    /**
     * <code>tokenHeaders</code>
     * {@link java.lang.String} <p>The <code>tokenHeaders</code> field.</p>
     * @see  java.lang.String
     */
    private String[] tokenHeaders = LoginConstants.HEADER_TOKENS;
    /**
     * <code>tokenExpiration</code>
     * {@link java.lang.Integer} <p>The <code>tokenExpiration</code> field.</p>
     * @see  java.lang.Integer
     */
    private Integer tokenExpiration = 1800;
    /**
     * <code>tokenTimeUnit</code>
     * {@link java.util.concurrent.TimeUnit} <p>The <code>tokenTimeUnit</code> field.</p>
     * @see  java.util.concurrent.TimeUnit
     */
    private TimeUnit tokenTimeUnit = TimeUnit.SECONDS;
    /**
     * <code>accessTokenHeader</code>
     * {@link java.lang.String} <p>The <code>accessTokenHeader</code> field.</p>
     * @see  java.lang.String
     */
    private String accessTokenHeader = LoginConstants.ACCESS_TOKEN_HEADER;
    /**
     * <code>accessAuthHeader</code>
     * {@link java.lang.String} <p>The <code>accessAuthHeader</code> field.</p>
     * @see  java.lang.String
     */
    private String accessAuthHeader = LoginConstants.ACCESS_AUTH_HEADER;

    /**
     * <code>RiceLoginProperties</code>
     * <p>Instantiates a new rice login properties.</p>
     */
    public RiceLoginProperties() {
    }

    /**
     * <code>getTokenPrefixes</code>
     * <p>The get token prefixes getter method.</p>
     * @return  {@link java.util.List} <p>The get token prefixes return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<String> getTokenPrefixes() {
        if (GeneralUtils.isNotEmpty(this.tokenPrefixes)) {
            return new ArrayList<>(Arrays.asList(tokenPrefixes));
        }
        return Collections.emptyList();
    }

    /**
     * <code>setTokenPrefixes</code>
     * <p>The set token prefixes setter method.</p>
     * @param tokenPrefixes {@link java.lang.String} <p>The token prefixes parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void setTokenPrefixes(String... tokenPrefixes) {
        this.tokenPrefixes = tokenPrefixes;
    }

    /**
     * <code>setTokenPrefixes</code>
     * <p>The set token prefixes setter method.</p>
     * @param tokenPrefixes {@link java.util.Collection} <p>The token prefixes parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
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
     * <p>The get skip urls getter method.</p>
     * @return  {@link java.util.List} <p>The get skip urls return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<String> getSkipUrls() {
        if (GeneralUtils.isNotEmpty(this.skipUrls)) {
            return new ArrayList<>(Arrays.asList(skipUrls));
        }
        return Collections.emptyList();
    }

    /**
     * <code>setSkipUrls</code>
     * <p>The set skip urls setter method.</p>
     * @param skipUrls {@link java.lang.String} <p>The skip urls parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void setSkipUrls(String... skipUrls) {
        this.skipUrls = skipUrls;
    }

    /**
     * <code>getTokenHeaders</code>
     * <p>The get token headers getter method.</p>
     * @return  {@link java.util.List} <p>The get token headers return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<String> getTokenHeaders() {
        if (GeneralUtils.isNotEmpty(this.tokenHeaders)) {
            return new ArrayList<>(Arrays.asList(tokenHeaders));
        }
        return Collections.emptyList();
    }

    /**
     * <code>setTokenHeaders</code>
     * <p>The set token headers setter method.</p>
     * @param tokenHeaders {@link java.lang.String} <p>The token headers parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void setTokenHeaders(String... tokenHeaders) {
        this.tokenHeaders = tokenHeaders;
    }

}
