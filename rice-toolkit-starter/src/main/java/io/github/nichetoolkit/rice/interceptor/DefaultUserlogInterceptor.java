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

@Slf4j
@Component
@Order(99)
public class DefaultUserlogInterceptor implements RequestHandleInterceptor {
    private final List<UserlogAdvice> userlogAdvices;

    public DefaultUserlogInterceptor() {
        this.userlogAdvices = new ArrayList<>();
    }

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
