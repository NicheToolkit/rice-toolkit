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
 * <p>The type default annotation interceptor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @see org.springframework.core.annotation.Order
 * @since Jdk1.8
 */
@Slf4j
@Component
@Order(AdviceConstants.ANNOTATION_ORDER)
public class DefaultAnnotationInterceptor implements RequestHandleInterceptor {
    /**
     * <code>defaultAdvices</code>
     * {@link java.util.List} <p>the <code>defaultAdvices</code> field.</p>
     * @see java.util.List
     */
    private final List<DefaultAdvice<? extends Annotation>> defaultAdvices;

    /**
     * <code>DefaultAnnotationInterceptor</code>
     * Instantiates a new default annotation interceptor.
     */
    public DefaultAnnotationInterceptor() {
        this.defaultAdvices = new ArrayList<>();
    }

    /**
     * <code>DefaultAnnotationInterceptor</code>
     * Instantiates a new default annotation interceptor.
     * @param defaultAdvices {@link java.util.List} <p>the default advices parameter is <code>List</code> type.</p>
     * @see java.util.List
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultAnnotationInterceptor(List<DefaultAdvice<?>> defaultAdvices) {
        this.defaultAdvices = defaultAdvices;
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (GeneralUtils.isNotEmpty(defaultAdvices)) {
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            defaultAdvices.sort(DefaultAdvice::compareTo);
            for (DefaultAdvice<?> defaultAdvice : defaultAdvices) {
                log.debug("advice type: {}", defaultAdvice.getClass().getName());
                adviceHandle(defaultAdvice, httpRequest, response, handlerMethod);
            }
        }

    }

    /**
     * <code>adviceHandle</code>
     * <p>the handle method.</p>
     * @param <A>           {@link java.lang.annotation.Annotation} <p>the generic parameter is <code>Annotation</code> type.</p>
     * @param defaultAdvice {@link io.github.nichetoolkit.rice.DefaultAdvice} <p>the default advice parameter is <code>DefaultAdvice</code> type.</p>
     * @param httpRequest   {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the http request parameter is <code>RestHttpRequest</code> type.</p>
     * @param response      {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>the handler method parameter is <code>HandlerMethod</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.annotation.Annotation
     * @see io.github.nichetoolkit.rice.DefaultAdvice
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see javax.servlet.http.HttpServletResponse
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
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
