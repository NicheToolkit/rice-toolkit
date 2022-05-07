package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.interceptor.advice.RiceModuleAdvice;
import io.github.nichetoolkit.rice.stereotype.purview.RestModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>RiceModuleInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
public class RiceModuleInterceptor implements RiceRequestInterceptor {
    private final List<RiceModuleAdvice> moduleAdvices;

    @Autowired(required = false)
    public RiceModuleInterceptor(List<RiceModuleAdvice> moduleAdvices) {
        this.moduleAdvices = moduleAdvices;
    }

    @Override
    public void afterHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (supports(RestModule.class,handlerMethod)) {
            RestModule restModule = annotation(RestModule.class, handlerMethod);
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(moduleAdvices)) {
                for (RiceModuleAdvice moduleAdvice : moduleAdvices) {
                    if (moduleAdvice.supports(restModule,handlerMethod)) {
                        moduleAdvice.checkModule(requestWrapper, response, handlerMethod,restModule);
                    }
                }
            }
        }
    }
}
