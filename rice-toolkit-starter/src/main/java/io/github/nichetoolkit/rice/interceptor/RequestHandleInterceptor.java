package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.RestSkip;
import io.github.nichetoolkit.rice.stereotype.login.RestLogin;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Optional;

public interface RequestHandleInterceptor extends AsyncHandlerInterceptor {
    String BASIC_ERROR = "org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController";

    @Override
    default boolean preHandle(@NonNull HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.getBean().getClass().getName().contains(BASIC_ERROR)) {
            return false;
        }
        if (RequestHandleInterceptor.supports(RestSkip.class, handlerMethod)) {
            return true;
        }
        beforeHandle(httpServletRequest, httpServletResponse, handlerMethod);
        if (handlerMethod.hasMethodAnnotation(RestLogin.class)) {
            return true;
        }
        afterHandle(httpServletRequest, httpServletResponse, handlerMethod);
        return true;
    }

    default void beforeHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
    }

    default void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
    }


    static <A extends Annotation> boolean supports(Class<A> annotationType, HandlerMethod handlerMethod) throws RestException {
        return handlerMethod.hasMethodAnnotation(annotationType) || GeneralUtils.isNotEmpty(handlerMethod.getBeanType().getAnnotation(annotationType));
    }

    static <A extends Annotation> A getAnnotation(Class<A> annotationType, HandlerMethod handlerMethod) throws RestException {
        A beanAnnotation = AnnotationUtils.getAnnotation(handlerMethod.getBeanType(), annotationType);
        A methodAnnotation = AnnotationUtils.getAnnotation(handlerMethod.getMethod(), annotationType);
        return Optional.ofNullable(methodAnnotation).orElse(beanAnnotation);
    }

}
