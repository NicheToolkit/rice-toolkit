package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.advice.annotation.AuthAdvice;
import io.github.nichetoolkit.rice.stereotype.login.RestAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>DefaultAuthInterceptor</code>
 * <p>The type default auth interceptor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @see org.springframework.core.annotation.Order
 * @since Jdk1.8
 */
@Slf4j
@Component
@Order(11)
public class DefaultAuthInterceptor implements RequestHandleInterceptor {
    private final List<AuthAdvice> authAdvices;

    /**
     * <code>DefaultAuthInterceptor</code>
     * Instantiates a new default auth interceptor.
     */
    public DefaultAuthInterceptor() {
        this.authAdvices = new ArrayList<>();
    }

    /**
     * <code>DefaultAuthInterceptor</code>
     * Instantiates a new default auth interceptor.
     * @param authAdvices {@link java.util.List} <p>the auth advices parameter is <code>List</code> type.</p>
     * @see java.util.List
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultAuthInterceptor(List<AuthAdvice> authAdvices) {
        this.authAdvices = authAdvices;
    }


    @Override
    public void beforeHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (RequestHandleInterceptor.supports(RestAuth.class, handlerMethod)) {
            RestAuth restAuth = RequestHandleInterceptor.getAnnotation(RestAuth.class, handlerMethod);
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            if (GeneralUtils.isNotEmpty(authAdvices)) {
                for (AuthAdvice authAdvice : authAdvices) {
                    if (authAdvice.supports(restAuth, handlerMethod)) {
                        authAdvice.doHandle(httpRequest, response, handlerMethod, restAuth);
                    }
                }
            }
        }
    }

}
