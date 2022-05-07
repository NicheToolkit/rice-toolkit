package io.github.nichetoolkit.rice.interceptor.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.RestAccess;
import io.github.nichetoolkit.rice.stereotype.purview.RestModule;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>RiceModuleAdvice </p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceModuleAdvice {

    default boolean supports(RestModule restModule, HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(restModule);
    }

    default void checkModule(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod, RestModule restModule) throws RestException {
    }

}
