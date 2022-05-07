package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import io.github.nichetoolkit.rice.interceptor.advice.RiceAccessAdvice;
import io.github.nichetoolkit.rice.stereotype.RestAccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>RiceAccessRequestInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
@Order(12)
public class RiceAccessInterceptor implements RiceRequestInterceptor {
    private final List<RiceAccessAdvice> accessAdvices;

    @Autowired(required = false)
    public RiceAccessInterceptor(List<RiceAccessAdvice> accessAdvices) {
        this.accessAdvices = accessAdvices;
    }

    @Override
    public void afterHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (InterceptorHelper.supports(RestAccess.class,handlerMethod)) {
            RestAccess restAccess = InterceptorHelper.annotation(RestAccess.class, handlerMethod);
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(accessAdvices)) {
                for (RiceAccessAdvice accessAdvice : accessAdvices) {
                    if (accessAdvice.supports(restAccess,handlerMethod)) {
                        accessAdvice.checkAccess(requestWrapper, response, handlerMethod,restAccess);
                    }
                }
            }
        }
    }
}
