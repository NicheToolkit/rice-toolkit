package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import io.github.nichetoolkit.rice.stereotype.RestAccess;
import io.github.nichetoolkit.rice.stereotype.RestCheck;
import io.github.nichetoolkit.rice.stereotype.RestSkip;
import io.github.nichetoolkit.rice.stereotype.login.RestAuth;
import io.github.nichetoolkit.rice.stereotype.login.RestLogin;
import io.github.nichetoolkit.rice.stereotype.purview.RestActor;
import io.github.nichetoolkit.rice.stereotype.purview.RestModule;
import io.github.nichetoolkit.rice.stereotype.purview.RestPermission;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * <p>RiceRequestInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceRequestInterceptor extends AsyncHandlerInterceptor {
    String BASIC_ERROR = "org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController";

    @Override
    default boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        /** 不是接口调用不拦截 */
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.getBean().getClass().getName().contains(BASIC_ERROR)) {
            return false;
        }
        if (InterceptorHelper.supports(RestSkip.class,handlerMethod)) {
            return true;
        }
        beforeHandle(httpServletRequest,httpServletResponse,handlerMethod);
        if (handlerMethod.hasMethodAnnotation(RestLogin.class)) {
            return true;
        }
        afterHandle(httpServletRequest,httpServletResponse,handlerMethod);
        return true;
    }

    default void beforeHandle(HttpServletRequest request, HttpServletResponse response,HandlerMethod handlerMethod) throws RestException {
    }

    default void afterHandle(HttpServletRequest request, HttpServletResponse response,HandlerMethod handlerMethod) throws RestException {
    }
}
