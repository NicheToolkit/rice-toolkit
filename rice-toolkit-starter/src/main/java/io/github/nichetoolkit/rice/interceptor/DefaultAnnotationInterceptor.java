package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestAfterLoginAdvice;
import io.github.nichetoolkit.rice.RestBeforeLoginAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * <code>DefaultAnnotationInterceptor</code>
 * <p>The default annotation interceptor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class DefaultAnnotationInterceptor implements RequestHandleInterceptor {
    /**
     * <code>beforeLoginAdvice</code>
     * {@link java.util.List} <p>The <code>beforeLoginAdvice</code> field.</p>
     * @see java.util.List
     */
    private final List<RestBeforeLoginAdvice<? extends Annotation>> beforeLoginAdvice;
    /**
     * <code>afterLoginAdvices</code>
     * {@link java.util.List} <p>The <code>afterLoginAdvices</code> field.</p>
     * @see java.util.List
     */
    private final List<RestAfterLoginAdvice<? extends Annotation>> afterLoginAdvices;

    /**
     * <code>DefaultAnnotationInterceptor</code>
     * <p>Instantiates a new default annotation interceptor.</p>
     * @param beforeLoginAdvice {@link java.util.List} <p>The before login advice parameter is <code>List</code> type.</p>
     * @param afterLoginAdvices {@link java.util.List} <p>The after login advices parameter is <code>List</code> type.</p>
     * @see java.util.List
     */
    public DefaultAnnotationInterceptor(List<RestBeforeLoginAdvice<? extends Annotation>> beforeLoginAdvice, List<RestAfterLoginAdvice<? extends Annotation>> afterLoginAdvices) {
        this.beforeLoginAdvice = beforeLoginAdvice;
        this.afterLoginAdvices = afterLoginAdvices;
    }

    @Override
    public void beforeHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (GeneralUtils.isNotEmpty(this.beforeLoginAdvice)) {
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            this.beforeLoginAdvice.sort(RestBeforeLoginAdvice::compareTo);
            for (RestBeforeLoginAdvice<?> beforeLoginAdvice : this.beforeLoginAdvice) {
                log.debug("The before advice       type: {}", beforeLoginAdvice.getClass().getName());
                adviceBeforeHandle(beforeLoginAdvice, httpRequest, response, handlerMethod);
            }
        }
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (GeneralUtils.isNotEmpty(this.afterLoginAdvices)) {
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            this.afterLoginAdvices.sort(RestAfterLoginAdvice::compareTo);
            for (RestAfterLoginAdvice<?> afterLoginAdvice : this.afterLoginAdvices) {
                log.debug("The after advice       type: {}", afterLoginAdvice.getClass().getName());
                adviceAfterHandle(afterLoginAdvice, httpRequest, response, handlerMethod);
            }
        }

    }

    /**
     * <code>adviceBeforeHandle</code>
     * <p>The advice before handle method.</p>
     * @param <A>               {@link java.lang.annotation.Annotation} <p>The generic parameter is <code>Annotation</code> type.</p>
     * @param beforeLoginAdvice {@link io.github.nichetoolkit.rice.RestBeforeLoginAdvice} <p>The before login advice parameter is <code>RestBeforeLoginAdvice</code> type.</p>
     * @param httpRequest       {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @param response          {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param handlerMethod     {@link org.springframework.web.method.HandlerMethod} <p>The handler method parameter is <code>HandlerMethod</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.annotation.Annotation
     * @see io.github.nichetoolkit.rice.RestBeforeLoginAdvice
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see javax.servlet.http.HttpServletResponse
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <A extends Annotation> void adviceBeforeHandle(RestBeforeLoginAdvice<A> beforeLoginAdvice, RestHttpRequest httpRequest, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        Class<A> clazz = beforeLoginAdvice.clazz();
        if (RequestHandleInterceptor.supports(clazz, handlerMethod)) {
            A annotation = RequestHandleInterceptor.getAnnotation(clazz, handlerMethod);
            if (beforeLoginAdvice.supports(annotation, handlerMethod)) {
                beforeLoginAdvice.doAnnotationHandle(httpRequest, response, handlerMethod, annotation);
            }
        }
    }

    /**
     * <code>adviceAfterHandle</code>
     * <p>The advice after handle method.</p>
     * @param <A>              {@link java.lang.annotation.Annotation} <p>The generic parameter is <code>Annotation</code> type.</p>
     * @param afterLoginAdvice {@link io.github.nichetoolkit.rice.RestAfterLoginAdvice} <p>The after login advice parameter is <code>RestAfterLoginAdvice</code> type.</p>
     * @param httpRequest      {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @param response         {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param handlerMethod    {@link org.springframework.web.method.HandlerMethod} <p>The handler method parameter is <code>HandlerMethod</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.annotation.Annotation
     * @see io.github.nichetoolkit.rice.RestAfterLoginAdvice
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see javax.servlet.http.HttpServletResponse
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <A extends Annotation> void adviceAfterHandle(RestAfterLoginAdvice<A> afterLoginAdvice, RestHttpRequest httpRequest, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        Class<A> clazz = afterLoginAdvice.clazz();
        if (RequestHandleInterceptor.supports(clazz, handlerMethod)) {
            A annotation = RequestHandleInterceptor.getAnnotation(clazz, handlerMethod);
            if (afterLoginAdvice.supports(annotation, handlerMethod)) {
                afterLoginAdvice.doAnnotationHandle(httpRequest, response, handlerMethod, annotation);
            }
        }
    }

}
