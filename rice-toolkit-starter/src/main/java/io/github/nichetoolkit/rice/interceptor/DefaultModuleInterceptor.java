package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.advice.annotation.ModuleAdvice;
import io.github.nichetoolkit.rice.stereotype.purview.RestModule;
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
 * <code>DefaultModuleInterceptor</code>
 * <p>The type default module interceptor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @see org.springframework.core.annotation.Order
 * @since Jdk1.8
 */
@Slf4j
@Component
@Order(13)
public class DefaultModuleInterceptor implements RequestHandleInterceptor {
    private final List<ModuleAdvice> moduleAdvices;

    /**
     * <code>DefaultModuleInterceptor</code>
     * Instantiates a new default module interceptor.
     */
    public DefaultModuleInterceptor() {
        this.moduleAdvices = new ArrayList<>();
    }

    /**
     * <code>DefaultModuleInterceptor</code>
     * Instantiates a new default module interceptor.
     * @param moduleAdvices {@link java.util.List} <p>the module advices parameter is <code>List</code> type.</p>
     * @see java.util.List
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultModuleInterceptor(List<ModuleAdvice> moduleAdvices) {
        this.moduleAdvices = moduleAdvices;
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (RequestHandleInterceptor.supports(RestModule.class, handlerMethod)) {
            RestModule restModule = RequestHandleInterceptor.getAnnotation(RestModule.class, handlerMethod);
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            if (GeneralUtils.isNotEmpty(moduleAdvices)) {
                for (ModuleAdvice moduleAdvice : moduleAdvices) {
                    if (moduleAdvice.supports(restModule, handlerMethod)) {
                        moduleAdvice.doHandle(httpRequest, response, handlerMethod, restModule);
                    }
                }
            }
        }
    }
}
