package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import io.github.nichetoolkit.rice.interceptor.advice.RiceWidgetAdvice;
import io.github.nichetoolkit.rice.stereotype.purview.RestWidget;
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
@Order(15)
public class RiceWidgetInterceptor implements RiceRequestInterceptor {
    private final List<RiceWidgetAdvice> widgetAdvices;

    @Autowired(required = false)
    public RiceWidgetInterceptor() {
        this.widgetAdvices = new ArrayList<>();
    }

    @Autowired(required = false)
    public RiceWidgetInterceptor(List<RiceWidgetAdvice> widgetAdvices) {
        this.widgetAdvices = widgetAdvices;
    }

    @Override
    public void afterHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (InterceptorHelper.supports(RestWidget.class,handlerMethod)) {
            RestWidget restWidget = InterceptorHelper.annotation(RestWidget.class, handlerMethod);
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(widgetAdvices)) {
                for (RiceWidgetAdvice widgetAdvice : widgetAdvices) {
                    if (widgetAdvice.supports(restWidget,handlerMethod)) {
                        widgetAdvice.checkWidget(requestWrapper, response, handlerMethod,restWidget);
                    }
                }
            }
        }
    }
}
