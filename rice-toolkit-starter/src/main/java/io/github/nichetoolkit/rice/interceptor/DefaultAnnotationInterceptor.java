package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.DefaultAdvice;
import io.github.nichetoolkit.rice.constant.AdviceConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>DefaultAnnotationInterceptor</code>
 * <p>The default annotation interceptor class.</p>
 * @see  io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.stereotype.Component
 * @see  org.springframework.core.annotation.Order
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@Component
@Order(AdviceConstants.ANNOTATION_ORDER)
public class DefaultAnnotationInterceptor implements RequestHandleInterceptor {
    /**
     * <code>defaultAdvices</code>
     * {@link java.util.List} <p>The <code>defaultAdvices</code> field.</p>
     * @see  java.util.List
     */
    private final List<DefaultAdvice<? extends Annotation>> defaultAdvices;

    /**
     * <code>DefaultAnnotationInterceptor</code>
     * <p>Instantiates a new default annotation interceptor.</p>
     */
    public DefaultAnnotationInterceptor() {
        this.defaultAdvices = new ArrayList<>();
    }

    /**
     * <code>DefaultAnnotationInterceptor</code>
     * <p>Instantiates a new default annotation interceptor.</p>
     * @param defaultAdvices {@link java.util.List} <p>The default advices parameter is <code>List</code> type.</p>
     * @see  java.util.List
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultAnnotationInterceptor(List<DefaultAdvice<?>> defaultAdvices) {
        this.defaultAdvices = defaultAdvices;
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (GeneralUtils.isNotEmpty(this.defaultAdvices)) {
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            this.defaultAdvices.sort(DefaultAdvice::compareTo);
            for (DefaultAdvice<?> defaultAdvice : this.defaultAdvices) {
                log.debug("The advice       type: {}", defaultAdvice.getClass().getName());
                adviceHandle(defaultAdvice, httpRequest, response, handlerMethod);
            }
        }

    }

    /**
     * <code>adviceHandle</code>
     * <p>The advice handle method.</p>
     * @param <A>  {@link java.lang.annotation.Annotation} <p>The generic parameter is <code>Annotation</code> type.</p>
     * @param defaultAdvice {@link io.github.nichetoolkit.rice.DefaultAdvice} <p>The default advice parameter is <code>DefaultAdvice</code> type.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>The handler method parameter is <code>HandlerMethod</code> type.</p>
     * @see  java.lang.annotation.Annotation
     * @see  io.github.nichetoolkit.rice.DefaultAdvice
     * @see  io.github.nichetoolkit.rest.RestHttpRequest
     * @see  javax.servlet.http.HttpServletResponse
     * @see  org.springframework.web.method.HandlerMethod
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public <A extends Annotation> void adviceHandle(DefaultAdvice<A> defaultAdvice, RestHttpRequest httpRequest, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        Class<A> clazz = defaultAdvice.clazz();
        if (RequestHandleInterceptor.supports(clazz, handlerMethod)) {
            A annotation = RequestHandleInterceptor.getAnnotation(clazz, handlerMethod);
            if (defaultAdvice.supports(annotation, handlerMethod)) {
                defaultAdvice.doAnnotationHandle(httpRequest, response, handlerMethod, annotation);
            }
        }
    }

}
