package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <code>HttpRequestHelper</code>
 * <p>The http request helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class HttpRequestHelper {

    /**
     * <code>resolveToken</code>
     * <p>The resolve token method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @see  javax.servlet.http.HttpServletRequest
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The resolve token return object is <code>String</code> type.</p>
     */
    public static String resolveToken(HttpServletRequest request) {
        if (GeneralUtils.isEmpty(request)) {
            return null;
        }
        List<String> tokenValues = resolveTokens(request, true);
        Optional<String> firstToken = tokenValues.stream().findFirst();
        return firstToken.orElse(null);
    }

    /**
     * <code>resolveAccessToken</code>
     * <p>The resolve access token method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @see  javax.servlet.http.HttpServletRequest
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The resolve access token return object is <code>String</code> type.</p>
     */
    public static String resolveAccessToken(HttpServletRequest request) {
        RiceLoginProperties loginProperties = BeanUtils.beanOfType(RiceLoginProperties.class);
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

    /**
     * <code>resolveAccessAuth</code>
     * <p>The resolve access auth method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @see  javax.servlet.http.HttpServletRequest
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The resolve access auth return object is <code>String</code> type.</p>
     */
    public static String resolveAccessAuth(HttpServletRequest request) {
        RiceLoginProperties loginProperties = BeanUtils.beanOfType(RiceLoginProperties.class);
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

    /**
     * <code>resolveTokens</code>
     * <p>The resolve tokens method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param isRemovePrefix boolean <p>The is remove prefix parameter is <code>boolean</code> type.</p>
     * @see  javax.servlet.http.HttpServletRequest
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The resolve tokens return object is <code>List</code> type.</p>
     */
    public static List<String> resolveTokens(HttpServletRequest request, boolean isRemovePrefix) {
        RiceLoginProperties loginProperties = BeanUtils.beanOfType(RiceLoginProperties.class);
        if (GeneralUtils.isEmpty(loginProperties)) {
            return Collections.emptyList();
        }
        List<String> tokenHeaders = loginProperties.getTokenHeaders();
        return resolveTokens(request,tokenHeaders,isRemovePrefix);
    }

    /**
     * <code>resolveTokens</code>
     * <p>The resolve tokens method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param tokenHeaders {@link java.util.List} <p>The token headers parameter is <code>List</code> type.</p>
     * @param isRemovePrefix boolean <p>The is remove prefix parameter is <code>boolean</code> type.</p>
     * @see  javax.servlet.http.HttpServletRequest
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The resolve tokens return object is <code>List</code> type.</p>
     */
    public static List<String> resolveTokens(HttpServletRequest request, List<String> tokenHeaders, boolean isRemovePrefix) {
        RiceLoginProperties loginProperties = BeanUtils.beanOfType(RiceLoginProperties.class);
        if (GeneralUtils.isEmpty(loginProperties)) {
            return Collections.emptyList();
        }
        if (tokenHeaders.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> prefixes = loginProperties.getTokenPrefixes();
        return resolveHeaders(request,tokenHeaders,prefixes,isRemovePrefix);
    }

    /**
     * <code>resolveHeaders</code>
     * <p>The resolve headers method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param headers {@link java.util.Collection} <p>The headers parameter is <code>Collection</code> type.</p>
     * @param prefixes {@link java.util.Collection} <p>The prefixes parameter is <code>Collection</code> type.</p>
     * @param isRemovePrefix boolean <p>The is remove prefix parameter is <code>boolean</code> type.</p>
     * @see  javax.servlet.http.HttpServletRequest
     * @see  java.util.Collection
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The resolve headers return object is <code>List</code> type.</p>
     */
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

    /**
     * <code>resolveAttributes</code>
     * <p>The resolve attributes method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param attributes {@link java.util.Collection} <p>The attributes parameter is <code>Collection</code> type.</p>
     * @see  javax.servlet.http.HttpServletRequest
     * @see  java.util.Collection
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The resolve attributes return object is <code>List</code> type.</p>
     */
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
