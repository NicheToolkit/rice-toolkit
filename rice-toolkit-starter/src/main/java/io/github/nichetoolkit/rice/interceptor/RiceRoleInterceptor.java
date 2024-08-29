package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import io.github.nichetoolkit.rice.interceptor.advice.RiceRoleAdvice;
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

@Slf4j
@Component
@Order(14)
public class RiceRoleInterceptor implements RiceRequestInterceptor {
    private final List<RiceRoleAdvice> roleAdvices;

    @Autowired(required = false)
    public RiceRoleInterceptor() {
        this.roleAdvices = new ArrayList<>();
    }

    @Autowired(required = false)
    public RiceRoleInterceptor(List<RiceRoleAdvice> roleAdvices) {
        this.roleAdvices = roleAdvices;
    }

    @Override
    public void afterHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (InterceptorHelper.supports(RestRole.class,handlerMethod)) {
            RestRole restActor = InterceptorHelper.annotation(RestRole.class, handlerMethod);
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(roleAdvices)) {
                for (RiceRoleAdvice roleAdvice : roleAdvices) {
                    if (roleAdvice.supports(restActor,handlerMethod)) {
                        roleAdvice.checkRole(requestWrapper, response, handlerMethod, restActor);
                    }
                }
            }
        }
    }
}
