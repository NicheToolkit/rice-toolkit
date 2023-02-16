package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import io.github.nichetoolkit.rice.interceptor.advice.RicePermissionAdvice;
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
 * <p>RicePermissionInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
@Order(15)
public class RicePermissionInterceptor implements RiceRequestInterceptor {
    private final List<RicePermissionAdvice> permissionAdvices;

    @Autowired(required = false)
    public RicePermissionInterceptor() {
        this.permissionAdvices = new ArrayList<>();
    }

    @Autowired(required = false)
    public RicePermissionInterceptor(List<RicePermissionAdvice> permissionAdvices) {
        this.permissionAdvices = permissionAdvices;
    }

    @Override
    public void afterHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (InterceptorHelper.supports(RestPurview.class,handlerMethod)) {
            RestPurview restPermission = InterceptorHelper.annotation(RestPurview.class, handlerMethod);
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(permissionAdvices)) {
                for (RicePermissionAdvice permissionAdvice : permissionAdvices) {
                    if (permissionAdvice.supports(restPermission,handlerMethod)) {
                        permissionAdvice.checkPermission(requestWrapper, response, handlerMethod,restPermission);
                    }
                }
            }
        }
    }
}
