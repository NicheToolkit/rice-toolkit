package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.advice.annotation.WidgetAdvice;
import io.github.nichetoolkit.rice.stereotype.purview.RestWidget;
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
 * <code>DefaultWidgetInterceptor</code>
 * <p>The type default widget interceptor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @see org.springframework.core.annotation.Order
 * @since Jdk1.8
 */
@Slf4j
@Component
@Order(15)
public class DefaultWidgetInterceptor implements RequestHandleInterceptor {
    private final List<WidgetAdvice> widgetAdvices;

    /**
     * <code>DefaultWidgetInterceptor</code>
     * Instantiates a new default widget interceptor.
     */
    public DefaultWidgetInterceptor() {
        this.widgetAdvices = new ArrayList<>();
    }

    /**
     * <code>DefaultWidgetInterceptor</code>
     * Instantiates a new default widget interceptor.
     * @param widgetAdvices {@link java.util.List} <p>the widget advices parameter is <code>List</code> type.</p>
     * @see java.util.List
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultWidgetInterceptor(List<WidgetAdvice> widgetAdvices) {
        this.widgetAdvices = widgetAdvices;
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request,@NonNull  HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (RequestHandleInterceptor.supports(RestWidget.class, handlerMethod)) {
            RestWidget restWidget = RequestHandleInterceptor.getAnnotation(RestWidget.class, handlerMethod);
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            if (GeneralUtils.isNotEmpty(widgetAdvices)) {
                for (WidgetAdvice widgetAdvice : widgetAdvices) {
                    if (widgetAdvice.supports(restWidget,handlerMethod)) {
                        widgetAdvice.doHandle(httpRequest, response, handlerMethod,restWidget);
                    }
                }
            }
        }
    }
}
