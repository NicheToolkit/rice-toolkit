package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class HttpRequestHelper {

    public static String resolveToken(HttpServletRequest request) {
        if (GeneralUtils.isEmpty(request)) {
            return null;
        }
        List<String> tokenValues = resolveTokens(request, true);
        Optional<String> firstToken = tokenValues.stream().findFirst();
        return firstToken.orElse(null);
    }

    public static String resolveAccessToken(HttpServletRequest request) {
        RiceLoginProperties loginProperties = ApplicationContextHolder.getBean(RiceLoginProperties.class);
        if (GeneralUtils.isNotEmpty(loginProperties)) {
            return null;
        }
        String accessTokenHeader = loginProperties.getAccessTokenHeader();
        if (GeneralUtils.isEmpty(accessTokenHeader)) {
            return null;
        }
        String accessToken = request.getHeader(accessTokenHeader);
        if (GeneralUtils.isEmpty(accessToken)) {
            return null;
        }
        return accessToken;
    }

    public static String resolveAccessAuth(HttpServletRequest request) {
        RiceLoginProperties loginProperties = ApplicationContextHolder.getBean(RiceLoginProperties.class);
        if (GeneralUtils.isNotEmpty(loginProperties)) {
            return null;
        }
        String accessAuthHeader = loginProperties.getAccessAuthHeader();
        if (GeneralUtils.isEmpty(accessAuthHeader)) {
            return null;
        }
        String accessAuth = request.getHeader(accessAuthHeader);
        if (GeneralUtils.isEmpty(accessAuthHeader)) {
            return null;
        }
        return accessAuth;
    }

    public static List<String> resolveTokens(HttpServletRequest request, boolean isRemovePrefix) {
        RiceLoginProperties loginProperties = ApplicationContextHolder.getBean(RiceLoginProperties.class);
        if (GeneralUtils.isNotEmpty(loginProperties)) {
            return Collections.emptyList();
        }
        List<String> tokenHeaders = loginProperties.getTokenHeaders();
        return resolveTokens(request,tokenHeaders,isRemovePrefix);
    }

    public static List<String> resolveTokens(HttpServletRequest request, List<String> tokenHeaders, boolean isRemovePrefix) {
        RiceLoginProperties loginProperties = ApplicationContextHolder.getBean(RiceLoginProperties.class);
        if (GeneralUtils.isNotEmpty(loginProperties)) {
            return Collections.emptyList();
        }
        if (tokenHeaders.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> prefixes = loginProperties.getTokenPrefixes();
        return resolveHeaders(request,tokenHeaders,prefixes,isRemovePrefix);
    }

    public static List<String> resolveHeaders(HttpServletRequest request, Collection<String> headers, Collection<String> prefixes, boolean isRemovePrefix) {
        List<String> values = new ArrayList<>();
        for (String key : headers) {
            if (GeneralUtils.isEmpty(key)) {
                continue;
            }
            String value = request.getHeader(key);
            if (GeneralUtils.isEmpty(value)) {
                continue;
            }
            if (isRemovePrefix && GeneralUtils.isNotEmpty(prefixes)) {
                for (String prefix : prefixes) {
                    if (value.startsWith(prefix)) {
                        value = value.replaceFirst(prefix, "").trim();
                        values.add(value);
                    }
                }
            } else {
                values.add(value);
            }
        }
        return values;
    }

    public static List<Object> resolveAttributes(HttpServletRequest request, Collection<String> attributes) {
        List<Object> values = new ArrayList<>();
        for (String attribute : attributes) {
            if (GeneralUtils.isEmpty(attribute)) {
                continue;
            }
            Object value = request.getAttribute(attribute);
            if (GeneralUtils.isNotEmpty(value)) {
                values.add(value);
            }
        }
        return values;
    }



}
