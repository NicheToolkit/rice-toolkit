package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.advice.annotation.RoleAdvice;
import io.github.nichetoolkit.rice.stereotype.purview.RestRole;
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
 * <code>DefaultRoleInterceptor</code>
 * <p>The type default role interceptor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @see org.springframework.core.annotation.Order
 * @since Jdk1.8
 */
@Slf4j
@Component
@Order(14)
public class DefaultRoleInterceptor implements RequestHandleInterceptor {
    private final List<RoleAdvice> roleAdvices;

    /**
     * <code>DefaultRoleInterceptor</code>
     * Instantiates a new default role interceptor.
     */
    public DefaultRoleInterceptor() {
        this.roleAdvices = new ArrayList<>();
    }

    /**
     * <code>DefaultRoleInterceptor</code>
     * Instantiates a new default role interceptor.
     * @param roleAdvices {@link java.util.List} <p>the role advices parameter is <code>List</code> type.</p>
     * @see java.util.List
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultRoleInterceptor(List<RoleAdvice> roleAdvices) {
        this.roleAdvices = roleAdvices;
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request,@NonNull  HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (RequestHandleInterceptor.supports(RestRole.class,handlerMethod)) {
            RestRole restRole = RequestHandleInterceptor.getAnnotation(RestRole.class, handlerMethod);
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            if (GeneralUtils.isNotEmpty(roleAdvices)) {
                for (RoleAdvice roleAdvice : roleAdvices) {
                    if (roleAdvice.supports(restRole,handlerMethod)) {
                        roleAdvice.doHandle(httpRequest, response, handlerMethod, restRole);
                    }
                }
            }
        }
    }
}
