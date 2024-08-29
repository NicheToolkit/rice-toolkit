package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.RestUser;
import org.springframework.core.MethodParameter;

public interface RiceUserAdvice {
    default boolean supports(MethodParameter parameter) {
        RestUser restUser = parameter.getParameterAnnotation(RestUser.class);
        return GeneralUtils.isNotEmpty(restUser);
    }

    RestUserInfo resolveArgument(MethodParameter parameter, RestRequestWrapper requestWrapper) throws RestException;
}
