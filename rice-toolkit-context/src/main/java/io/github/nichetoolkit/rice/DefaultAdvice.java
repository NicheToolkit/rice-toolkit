package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.resolver.RestGenericTypeResolver;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * <code>DefaultAdvice</code>
 * <p>The type default advice interface.</p>
 * @param <A> {@link java.lang.annotation.Annotation} <p>the generic parameter is <code>Annotation</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.Comparable
 * @since Jdk1.8
 */
public interface DefaultAdvice<A extends Annotation> extends Comparable<DefaultAdvice<A>>{

    /**
     * <code>supports</code>
     * <p>the method.</p>
     * @param annotation    A <p>the annotation parameter is <code>A</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>the handler method parameter is <code>HandlerMethod</code> type.</p>
     * @return boolean <p>the return object is <code>boolean</code> type.</p>
     * @see org.springframework.web.method.HandlerMethod
     */
    default boolean supports(A annotation, HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(annotation);
    }

    /**
     * <code>doAnnotationHandle</code>
     * <p>the annotation handle method.</p>
     * @param request       {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request parameter is <code>RestHttpRequest</code> type.</p>
     * @param response      {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>the handler method parameter is <code>HandlerMethod</code> type.</p>
     * @param annotation    A <p>the annotation parameter is <code>A</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see javax.servlet.http.HttpServletResponse
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void doAnnotationHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod, A annotation) throws RestException {
    }

    /**
     * <code>clazz</code>
     * <p>the method.</p>
     * @return {@link java.lang.Class} <p>the return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    default Class<A> clazz() {
        return (Class<A>) RestGenericTypeResolver.resolveClass(RestGenericTypeResolver.resolveType(
                DefaultAdvice.class.getTypeParameters()[0], getClass(), DefaultAdvice.class));
    }

    /**
     * <code>order</code>
     * <p>the method.</p>
     * @return int <p>the return object is <code>int</code> type.</p>
     */
    int order();

    @Override
    default int compareTo(DefaultAdvice advice) {
        return Integer.compare(this.order(),advice.order());
    }

}
