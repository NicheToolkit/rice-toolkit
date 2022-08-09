package io.github.nichetoolkit.rice.interceptor.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.web.method.HandlerMethod;

/**
 * <p>RiceUserlogAdvice </p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceUserlogAdvice {

    default boolean supports(RestUserlog userlog, HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(userlog);
    }

    default void userlog(HandlerMethod handlerMethod, RestRequestWrapper requestWrapper) throws RestException {
    }

}
