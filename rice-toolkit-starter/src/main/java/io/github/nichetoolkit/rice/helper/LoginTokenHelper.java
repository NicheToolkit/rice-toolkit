package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>LoginTokenHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class LoginTokenHelper {
    private static RiceLoginProperties LOGIN_PROPERTIES;

    public static void init(RiceLoginProperties loginProperties) {
        LOGIN_PROPERTIES = loginProperties;
    }

    public static String getRequestToken(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        List<String> tokenList = getHeaderToken(request,true);
        if (tokenList.isEmpty()) {
            return null;
        }
        return tokenList.get(0);
    }

    public static List<String> getHeaderToken(HttpServletRequest request, List<String> headerTokens, boolean removePrefix) {
        List<String> tokenPrefixes = LOGIN_PROPERTIES.getTokenPrefixes();
        List<String> tokenList = new ArrayList<>(2);
        for (String tokenName : headerTokens) {
            if (GeneralUtils.isEmpty(tokenName)) {
                continue;
            }
            String token = request.getHeader(tokenName);
            if (GeneralUtils.isNotEmpty(token)) {
                if (removePrefix && GeneralUtils.isNotEmpty(tokenPrefixes)) {
                    for (String tokenPrefix : tokenPrefixes) {
                        if (token.startsWith(tokenPrefix)) {
                            token = token.replaceFirst(tokenPrefix,"").trim();
                            tokenList.add(token);
                        }
                    }
                } else {
                    tokenList.add(token);
                }
                break;
            }
        }
        return tokenList;
    }

    public static List<String> getHeaderToken(HttpServletRequest request, boolean removePrefix) {
        List<String> headerTokens = LOGIN_PROPERTIES.getHeaderTokens();
        if (headerTokens.isEmpty()) {
            return new ArrayList<>(0);
        }
        return getHeaderToken(request, headerTokens,removePrefix);
    }
}
