package io.github.nichetoolkit.rice.interceptor.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.purview.RestRole;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>RiceActorAdvice </p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceActorAdvice {

    default boolean supports(RestRole restActor, HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(restActor);
    }

    default void checkActor(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod, RestRole restActor) throws RestException {
    }

}
