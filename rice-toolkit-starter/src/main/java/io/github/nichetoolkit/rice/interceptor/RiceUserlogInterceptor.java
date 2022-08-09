package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import io.github.nichetoolkit.rice.interceptor.advice.RiceUserlogAdvice;
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
 * <p>RiceUserlogInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
@Order(99)
public class RiceUserlogInterceptor implements RiceRequestInterceptor {
    private final List<RiceUserlogAdvice> userlogAdvices;

    @Autowired(required = false)
    public RiceUserlogInterceptor() {
        this.userlogAdvices = new ArrayList<>();
    }

    @Autowired(required = false)
    public RiceUserlogInterceptor(List<RiceUserlogAdvice> userlogAdvices) {
        this.userlogAdvices = userlogAdvices;
    }

    @Override
    public void afterHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (InterceptorHelper.supports(RestUserlog.class,handlerMethod)) {
            RestUserlog userlog = InterceptorHelper.annotation(RestUserlog.class, handlerMethod);
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(this.userlogAdvices)) {
                for (RiceUserlogAdvice userlogAdvice : this.userlogAdvices) {
                    if (userlogAdvice.supports(userlog,handlerMethod)) {
                        userlogAdvice.userlog(handlerMethod,requestWrapper);
                    }
                }
            }
        }
    }
}
