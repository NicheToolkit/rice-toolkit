package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import io.github.nichetoolkit.rice.interceptor.advice.RiceAuthAdvice;
import io.github.nichetoolkit.rice.stereotype.login.RestAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Order(11)
public class RiceAuthInterceptor implements RiceRequestInterceptor {
    private final List<RiceAuthAdvice> authAdvices;

    @Autowired(required = false)
    public RiceAuthInterceptor() {
        this.authAdvices = new ArrayList<>();
    }

    @Autowired(required = false)
    public RiceAuthInterceptor(List<RiceAuthAdvice> authAdvices) {
        this.authAdvices = authAdvices;
    }


    @Override
    public void beforeHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (InterceptorHelper.supports(RestAuth.class,handlerMethod)) {
            RestAuth restAuth = InterceptorHelper.annotation(RestAuth.class, handlerMethod);
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(authAdvices)) {
                for (RiceAuthAdvice authAdvice : authAdvices) {
                    if (authAdvice.supports(restAuth,handlerMethod)) {
                        authAdvice.checkAuth(requestWrapper, response, handlerMethod,restAuth);
                    }
                }
            }
        }
    }
    
}
