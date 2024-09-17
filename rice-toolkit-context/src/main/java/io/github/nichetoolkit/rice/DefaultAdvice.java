package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.resolver.RestGenericTypeResolver;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

public interface DefaultAdvice<A extends Annotation> extends Comparable<DefaultAdvice<A>>{

    default boolean supports(A annotation, HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(annotation);
    }

    default void doAnnotationHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod, A annotation) throws RestException {
    }

    @SuppressWarnings(value = "unchecked")
    default Class<A> clazz() {
        return (Class<A>) RestGenericTypeResolver.resolveClass(RestGenericTypeResolver.resolveType(
                DefaultAdvice.class.getTypeParameters()[0], getClass(), DefaultAdvice.class));
    }

    int order();

    @Override
    default int compareTo(DefaultAdvice advice) {
        return Integer.compare(this.order(),advice.order());
    }

}
