package io.github.nichetoolkit.rice.advice.annotation;

import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rice.DefaultAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * <code>UserlogAdvice</code>
 * <p>The type userlog advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultAdvice
 * @since Jdk1.8
 */
public interface UserlogAdvice extends DefaultAdvice<RestUserlog> {

    @Override
    default void doHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod, RestUserlog annotation) throws RestException {
        doUserlogHandle(request, handlerMethod);
    }

    /**
     * <code>doUserlogHandle</code>
     * <p>the userlog handle method.</p>
     * @param request       {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request parameter is <code>RestHttpRequest</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>the handler method parameter is <code>HandlerMethod</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void doUserlogHandle(RestHttpRequest request, HandlerMethod handlerMethod) throws RestException {
    }

}
