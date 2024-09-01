package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestUserInfo;
import io.github.nichetoolkit.rice.stereotype.RestUser;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;

public interface RestUserResolver {

    default boolean supports(MethodParameter parameter) {
        RestUser restUser = parameter.getParameterAnnotation(RestUser.class);
        return GeneralUtils.isNotEmpty(restUser);
    }

    @NonNull
    RestUserInfo<?> resolveUser(MethodParameter parameter, RestHttpRequest httpRequest) throws RestException;
}
