package io.github.nichetoolkit.rice.interceptor.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.purview.RestRole;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>RiceRoleAdvice </p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceRoleAdvice {

    default boolean supports(RestRole restRole, HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(restRole);
    }

    default void checkRole(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod, RestRole restRole) throws RestException {
    }

}
