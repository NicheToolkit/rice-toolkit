package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import io.github.nichetoolkit.rice.interceptor.advice.RicePurviewAdvice;
import io.github.nichetoolkit.rice.stereotype.purview.RestPurview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>RicePurviewInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
@Order(15)
public class RicePurviewInterceptor implements RiceRequestInterceptor {
    private final List<RicePurviewAdvice> purviewAdvices;

    @Autowired(required = false)
    public RicePurviewInterceptor() {
        this.purviewAdvices = new ArrayList<>();
    }

    @Autowired(required = false)
    public RicePurviewInterceptor(List<RicePurviewAdvice> purviewAdvices) {
        this.purviewAdvices = purviewAdvices;
    }

    @Override
    public void afterHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (InterceptorHelper.supports(RestPurview.class,handlerMethod)) {
            RestPurview restPurview = InterceptorHelper.annotation(RestPurview.class, handlerMethod);
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(purviewAdvices)) {
                for (RicePurviewAdvice purviewAdvice : purviewAdvices) {
                    if (purviewAdvice.supports(restPurview,handlerMethod)) {
                        purviewAdvice.checkPurview(requestWrapper, response, handlerMethod,restPurview);
                    }
                }
            }
        }
    }
}
