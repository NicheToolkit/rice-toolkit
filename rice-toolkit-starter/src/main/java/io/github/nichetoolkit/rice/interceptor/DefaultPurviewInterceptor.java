package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.advice.annotation.PurviewAdvice;
import io.github.nichetoolkit.rice.stereotype.purview.RestPurview;
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
@Order(16)
public class DefaultPurviewInterceptor implements RequestHandleInterceptor {
    private final List<PurviewAdvice> purviewAdvices;

    public DefaultPurviewInterceptor() {
        this.purviewAdvices = new ArrayList<>();
    }

    @Autowired(required = false)
    public DefaultPurviewInterceptor(List<PurviewAdvice> purviewAdvices) {
        this.purviewAdvices = purviewAdvices;
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (RequestHandleInterceptor.supports(RestPurview.class, handlerMethod)) {
            RestPurview restPurview = RequestHandleInterceptor.getAnnotation(RestPurview.class, handlerMethod);
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            if (GeneralUtils.isNotEmpty(purviewAdvices)) {
                for (PurviewAdvice purviewAdvice : purviewAdvices) {
                    if (purviewAdvice.supports(restPurview,handlerMethod)) {
                        purviewAdvice.doHandle(httpRequest, response, handlerMethod,restPurview);
                    }
                }
            }
        }
    }
}
