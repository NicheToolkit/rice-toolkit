package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestAfterLoginAdvice;
import io.github.nichetoolkit.rice.RestBeforeLoginAdvice;
import io.github.nichetoolkit.rice.RestBeforeLogoutAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.List;

@Slf4j
public class DefaultAnnotationInterceptor implements RequestHandleInterceptor {
    private final List<RestBeforeLogoutAdvice<? extends Annotation>> beforeLogoutAdvice;
    private final List<RestBeforeLoginAdvice<? extends Annotation>> beforeLoginAdvice;
    private final List<RestAfterLoginAdvice<? extends Annotation>> afterLoginAdvices;

    public DefaultAnnotationInterceptor(List<RestBeforeLogoutAdvice<? extends Annotation>> beforeLogoutAdvice,List<RestBeforeLoginAdvice<? extends Annotation>> beforeLoginAdvice, List<RestAfterLoginAdvice<? extends Annotation>> afterLoginAdvices) {
        this.beforeLogoutAdvice = beforeLogoutAdvice;
        this.beforeLoginAdvice = beforeLoginAdvice;
        this.afterLoginAdvices = afterLoginAdvices;
    }

    @Override
    public void logoutHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (GeneralUtils.isNotEmpty(this.beforeLogoutAdvice)) {
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            this.beforeLogoutAdvice.sort(RestBeforeLogoutAdvice::compareTo);
            for (RestBeforeLogoutAdvice<?> beforeLogoutAdvice : this.beforeLogoutAdvice) {
                log.debug("The logout advice       type: {}", beforeLogoutAdvice.getClass().getName());
                adviceLogoutHandle(beforeLogoutAdvice, httpRequest, response, handlerMethod);
            }
        }
    }

    @Override
    public void beforeHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (GeneralUtils.isNotEmpty(this.beforeLoginAdvice)) {
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            this.beforeLoginAdvice.sort(RestBeforeLoginAdvice::compareTo);
            for (RestBeforeLoginAdvice<?> beforeLoginAdvice : this.beforeLoginAdvice) {
                log.debug("The before advice       type: {}", beforeLoginAdvice.getClass().getName());
                adviceBeforeHandle(beforeLoginAdvice, httpRequest, response, handlerMethod);
            }
        }
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (GeneralUtils.isNotEmpty(this.afterLoginAdvices)) {
            RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
            this.afterLoginAdvices.sort(RestAfterLoginAdvice::compareTo);
            for (RestAfterLoginAdvice<?> afterLoginAdvice : this.afterLoginAdvices) {
                log.debug("The after advice       type: {}", afterLoginAdvice.getClass().getName());
                adviceAfterHandle(afterLoginAdvice, httpRequest, response, handlerMethod);
            }
        }

    }

    public <A extends Annotation> void adviceLogoutHandle(RestBeforeLogoutAdvice<A> beforeLogoutAdvice, RestHttpRequest httpRequest, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        Class<A> clazz = beforeLogoutAdvice.clazz();
        if (RequestHandleInterceptor.supports(clazz, handlerMethod)) {
            A annotation = RequestHandleInterceptor.getAnnotation(clazz, handlerMethod);
            if (beforeLogoutAdvice.supports(annotation, handlerMethod)) {
                beforeLogoutAdvice.doAnnotationHandle(httpRequest, response, handlerMethod, annotation);
            }
        }
    }

    public <A extends Annotation> void adviceBeforeHandle(RestBeforeLoginAdvice<A> beforeLoginAdvice, RestHttpRequest httpRequest, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        Class<A> clazz = beforeLoginAdvice.clazz();
        if (RequestHandleInterceptor.supports(clazz, handlerMethod)) {
            A annotation = RequestHandleInterceptor.getAnnotation(clazz, handlerMethod);
            if (beforeLoginAdvice.supports(annotation, handlerMethod)) {
                beforeLoginAdvice.doAnnotationHandle(httpRequest, response, handlerMethod, annotation);
            }
        }
    }

    public <A extends Annotation> void adviceAfterHandle(RestAfterLoginAdvice<A> afterLoginAdvice, RestHttpRequest httpRequest, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        Class<A> clazz = afterLoginAdvice.clazz();
        if (RequestHandleInterceptor.supports(clazz, handlerMethod)) {
            A annotation = RequestHandleInterceptor.getAnnotation(clazz, handlerMethod);
            if (afterLoginAdvice.supports(annotation, handlerMethod)) {
                afterLoginAdvice.doAnnotationHandle(httpRequest, response, handlerMethod, annotation);
            }
        }
    }

}
