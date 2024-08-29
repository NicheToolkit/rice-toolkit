package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.login")
public class RiceLoginProperties {
    private Boolean enabled = false;

    private String[] tokenPrefixes;
    private String[] skipUrls;
    private String[] headerTokens;
    private Integer tokenExpiration = 1800;
    private TimeUnit tokenTimeUnit = TimeUnit.SECONDS;

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
            String[] prefixArray= new String[tokenPrefixes.size()];
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

    public List<String> getHeaderTokens() {
        if (GeneralUtils.isNotEmpty(this.headerTokens)) {
            return new ArrayList<>(Arrays.asList(headerTokens));
        }
        return Collections.emptyList();
    }

    public void setHeaderTokens(String... headerTokens) {
        this.headerTokens = headerTokens;
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
}
