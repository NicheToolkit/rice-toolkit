package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import io.github.nichetoolkit.rice.interceptor.advice.RiceActorAdvice;
import io.github.nichetoolkit.rice.stereotype.purview.RestRole;
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
 * <p>RiceActorInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
@Order(14)
public class RiceActorInterceptor implements RiceRequestInterceptor {
    private final List<RiceActorAdvice> actorAdvices;

    @Autowired(required = false)
    public RiceActorInterceptor() {
        this.actorAdvices = new ArrayList<>();
    }

    @Autowired(required = false)
    public RiceActorInterceptor(List<RiceActorAdvice> actorAdvices) {
        this.actorAdvices = actorAdvices;
    }

    @Override
    public void afterHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (InterceptorHelper.supports(RestRole.class,handlerMethod)) {
            RestRole restActor = InterceptorHelper.annotation(RestRole.class, handlerMethod);
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(actorAdvices)) {
                for (RiceActorAdvice actorAdvice : actorAdvices) {
                    if (actorAdvice.supports(restActor,handlerMethod)) {
                        actorAdvice.checkActor(requestWrapper, response, handlerMethod, restActor);
                    }
                }
            }
        }
    }
}
