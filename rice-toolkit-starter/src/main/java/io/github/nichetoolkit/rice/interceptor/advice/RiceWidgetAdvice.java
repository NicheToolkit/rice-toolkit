package io.github.nichetoolkit.rice.interceptor.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.purview.RestWidget;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

public interface RiceWidgetAdvice {

    default boolean supports(RestWidget restWidget, HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(restWidget);
    }

    default void checkWidget(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod, RestWidget restWidget) throws RestException {
    }

}
