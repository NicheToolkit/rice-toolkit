package io.github.nichetoolkit.rice.interceptor.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.RestAccess;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>RiceAccessAdvice </p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceAccessAdvice {

    default boolean supports(RestAccess restAccess,HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(restAccess);
    }

    default void checkAccess(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod,RestAccess restAccess) throws RestException {
    }
}
