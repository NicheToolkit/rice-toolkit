package io.github.nichetoolkit.rice.interceptor.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.login.RestAuth;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>RiceAuthAdvice </p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceAuthAdvice {

    default boolean supports(RestAuth restAuth, HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(restAuth);
    }

    default void checkAuth(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod, RestAuth restAuth) throws RestException {
    }
}
