package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

public interface DefaultAdvice<A extends Annotation> {

    default boolean supports(A annotation, HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(annotation);
    }

    default void doHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod, A annotation) throws RestException {
    }
}
