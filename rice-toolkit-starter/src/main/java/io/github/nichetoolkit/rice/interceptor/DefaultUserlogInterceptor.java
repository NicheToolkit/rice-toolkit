package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.advice.annotation.UserlogAdvice;
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
 * <code>DefaultUserlogInterceptor</code>
 * <p>The type default userlog interceptor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @see org.springframework.core.annotation.Order
 * @since Jdk1.8
 */
@Slf4j
@Component
@Order(99)
public class DefaultUserlogInterceptor implements RequestHandleInterceptor {
    private final List<UserlogAdvice> userlogAdvices;

    /**
     * <code>DefaultUserlogInterceptor</code>
     * Instantiates a new default userlog interceptor.
     */
    public DefaultUserlogInterceptor() {
        this.userlogAdvices = new ArrayList<>();
    }

    /**
     * <code>DefaultUserlogInterceptor</code>
     * Instantiates a new default userlog interceptor.
     * @param userlogAdvices {@link java.util.List} <p>the userlog advices parameter is <code>List</code> type.</p>
     * @see java.util.List
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultUserlogInterceptor(List<UserlogAdvice> userlogAdvices) {
        this.userlogAdvices = userlogAdvices;
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (RequestHandleInterceptor.supports(RestUserlog.class, handlerMethod)) {
            RestUserlog restUserlog = RequestHandleInterceptor.getAnnotation(RestUserlog.class, handlerMethod);
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            if (GeneralUtils.isNotEmpty(this.userlogAdvices)) {
                for (UserlogAdvice userlogAdvice : this.userlogAdvices) {
                    if (userlogAdvice.supports(restUserlog, handlerMethod)) {
                        userlogAdvice.doHandle(httpRequest, response, handlerMethod, restUserlog);
                    }
                }
            }
        }
    }
}
