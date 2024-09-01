package io.github.nichetoolkit.rice.advice.annotation;

import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rice.DefaultAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

public interface UserlogAdvice extends DefaultAdvice<RestUserlog> {

    @Override
    default void doHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod, RestUserlog annotation) throws RestException {
        doUserlogHandle(request, handlerMethod);
    }

    default void doUserlogHandle(RestHttpRequest request, HandlerMethod handlerMethod) throws RestException {
    }

}
