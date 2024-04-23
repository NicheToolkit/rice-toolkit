package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import io.github.nichetoolkit.rice.stereotype.mybatis.table.RestEntity;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>LoginTokenHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class InterceptorHelper {
    private static RiceLoginProperties LOGIN_PROPERTIES;

    public static void init(RiceLoginProperties loginProperties) {
        LOGIN_PROPERTIES = loginProperties;
    }

    public static <A extends Annotation> boolean supports(Class<A> annotationType, HandlerMethod handlerMethod) throws RestException {
        return handlerMethod.hasMethodAnnotation(annotationType) || GeneralUtils.isNotEmpty(handlerMethod.getBeanType().getAnnotation(annotationType));
    }

    public static <A extends Annotation> A annotation(Class<A> annotationType, HandlerMethod handlerMethod) throws RestException {
        Class<?> beanType = handlerMethod.getBeanType();
        A beanAnnotation = AnnotationUtils.getAnnotation(beanType, annotationType);
        A methodAnnotation = AnnotationUtils.getAnnotation(handlerMethod.getMethod(), annotationType);
        A annotation;
        if (GeneralUtils.isNotEmpty(methodAnnotation)) {
            annotation = methodAnnotation;
        } else {
            annotation = beanAnnotation;
        }
        return annotation;
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
        if (GeneralUtils.isEmpty(LOGIN_PROPERTIES)) {
            return Collections.emptyList();
        }
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
        if (GeneralUtils.isEmpty(LOGIN_PROPERTIES)) {
            return Collections.emptyList();
        }
        List<String> headerTokens = LOGIN_PROPERTIES.getHeaderTokens();
        if (headerTokens.isEmpty()) {
            return Collections.emptyList();
        }
        return getHeaderToken(request, headerTokens,removePrefix);
    }
}
