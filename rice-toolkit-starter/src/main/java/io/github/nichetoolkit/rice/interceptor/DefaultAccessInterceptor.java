package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.advice.annotation.AccessAdvice;
import io.github.nichetoolkit.rice.stereotype.RestAccess;
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
@Order(12)
public class DefaultAccessInterceptor implements RequestHandleInterceptor {
    private final List<AccessAdvice> accessAdvices;

    public DefaultAccessInterceptor() {
        this.accessAdvices = new ArrayList<>();
    }

    @Autowired(required = false)
    public DefaultAccessInterceptor(List<AccessAdvice> accessAdvices) {
        this.accessAdvices = accessAdvices;
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (RequestHandleInterceptor.supports(RestAccess.class,handlerMethod)) {
            RestAccess restAccess = RequestHandleInterceptor.getAnnotation(RestAccess.class, handlerMethod);
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            if (GeneralUtils.isNotEmpty(accessAdvices)) {
                for (AccessAdvice accessAdvice : accessAdvices) {
                    if (accessAdvice.supports(restAccess,handlerMethod)) {
                        accessAdvice.doHandle(httpRequest, response, handlerMethod,restAccess);
                    }
                }
            }
        }
    }
}
