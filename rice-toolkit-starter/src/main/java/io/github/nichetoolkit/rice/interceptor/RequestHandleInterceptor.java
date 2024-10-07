package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.RestSkip;
import io.github.nichetoolkit.rice.stereotype.RestLogin;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * <code>RequestHandleInterceptor</code>
 * <p>The type request handle interceptor interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.AsyncHandlerInterceptor
 * @since Jdk1.8
 */
public interface RequestHandleInterceptor extends AsyncHandlerInterceptor {
    /**
     * <code>BASIC_ERROR</code>
     * {@link java.lang.String} <p>The constant <code>BASIC_ERROR</code> field.</p>
     * @see java.lang.String
     */
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

    /**
     * <code>beforeHandle</code>
     * <p>The handle method.</p>
     * @param request       {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response      {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>The handler method parameter is <code>HandlerMethod</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see org.springframework.lang.NonNull
     * @see javax.servlet.http.HttpServletResponse
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void beforeHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
    }

    /**
     * <code>afterHandle</code>
     * <p>The handle method.</p>
     * @param request       {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response      {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>The handler method parameter is <code>HandlerMethod</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see org.springframework.lang.NonNull
     * @see javax.servlet.http.HttpServletResponse
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
    }


    /**
     * <code>supports</code>
     * <p>The method.</p>
     * @param <A>            {@link java.lang.annotation.Annotation} <p>The generic parameter is <code>Annotation</code> type.</p>
     * @param annotationType {@link java.lang.Class} <p>The annotation type parameter is <code>Class</code> type.</p>
     * @param handlerMethod  {@link org.springframework.web.method.HandlerMethod} <p>The handler method parameter is <code>HandlerMethod</code> type.</p>
     * @return boolean <p>The return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.annotation.Annotation
     * @see java.lang.Class
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <A extends Annotation> boolean supports(Class<A> annotationType, HandlerMethod handlerMethod) throws RestException {
        return handlerMethod.hasMethodAnnotation(annotationType) || GeneralUtils.isNotEmpty(handlerMethod.getBeanType().getAnnotation(annotationType));
    }

    /**
     * <code>getAnnotation</code>
     * <p>The annotation getter method.</p>
     * @param <A>            {@link java.lang.annotation.Annotation} <p>The generic parameter is <code>Annotation</code> type.</p>
     * @param annotationType {@link java.lang.Class} <p>The annotation type parameter is <code>Class</code> type.</p>
     * @param handlerMethod  {@link org.springframework.web.method.HandlerMethod} <p>The handler method parameter is <code>HandlerMethod</code> type.</p>
     * @return A <p>The annotation return object is <code>A</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.annotation.Annotation
     * @see java.lang.Class
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <A extends Annotation> A getAnnotation(Class<A> annotationType, HandlerMethod handlerMethod) throws RestException {
        A beanAnnotation = AnnotationUtils.getAnnotation(handlerMethod.getBeanType(), annotationType);
        A methodAnnotation = AnnotationUtils.getAnnotation(handlerMethod.getMethod(), annotationType);
        return Optional.ofNullable(methodAnnotation).orElse(beanAnnotation);
    }

}
