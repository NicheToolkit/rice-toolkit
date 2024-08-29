package io.github.nichetoolkit.rice.interceptor.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.purview.RestPurview;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

public interface RicePurviewAdvice {

    default boolean supports(RestPurview restPermission, HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(restPermission);
    }

    default void checkPurview(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod, RestPurview restPurview) throws RestException {
    }

}
