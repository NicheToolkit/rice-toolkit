package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.constant.LoginConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

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

    public RiceLoginProperties() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getTokenPrefixes() {
        if (GeneralUtils.isNotEmpty(this.tokenPrefixes)) {
            return new ArrayList<>(Arrays.asList(tokenPrefixes));
        }
        return Collections.emptyList();
    }

    public void setTokenPrefixes(String... tokenPrefixes) {
        this.tokenPrefixes = tokenPrefixes;
    }

    public void setTokenPrefixes(Collection<String> tokenPrefixes) {
        if (GeneralUtils.isNotEmpty(tokenPrefixes)) {
            String[] prefixArray = new String[tokenPrefixes.size()];
            tokenPrefixes.toArray(prefixArray);
            this.tokenPrefixes = prefixArray;
        }
    }

    public List<String> getSkipUrls() {
        if (GeneralUtils.isNotEmpty(this.skipUrls)) {
            return new ArrayList<>(Arrays.asList(skipUrls));
        }
        return Collections.emptyList();
    }

    public void setSkipUrls(String... skipUrls) {
        this.skipUrls = skipUrls;
    }

    public List<String> getTokenHeaders() {
        if (GeneralUtils.isNotEmpty(this.tokenHeaders)) {
            return new ArrayList<>(Arrays.asList(tokenHeaders));
        }
        return Collections.emptyList();
    }

    public void setTokenHeaders(String... tokenHeaders) {
        this.tokenHeaders = tokenHeaders;
    }

    public Integer getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Integer tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public TimeUnit getTokenTimeUnit() {
        return tokenTimeUnit;
    }

    public void setTokenTimeUnit(TimeUnit tokenTimeUnit) {
        this.tokenTimeUnit = tokenTimeUnit;
    }

    public String getAccessTokenHeader() {
        return accessTokenHeader;
    }

    public void setAccessTokenHeader(String accessTokenHeader) {
        this.accessTokenHeader = accessTokenHeader;
    }

    public String getAccessAuthHeader() {
        return accessAuthHeader;
    }

    public void setAccessAuthHeader(String accessAuthHeader) {
        this.accessAuthHeader = accessAuthHeader;
    }
}
