package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestUserInfo;
import io.github.nichetoolkit.rice.stereotype.RestUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DefaultUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final List<RestUserResolver> userResolvers;

    public DefaultUserArgumentResolver() {
        this.userResolvers = new ArrayList<>();
    }

    @Autowired(required = false)
    public DefaultUserArgumentResolver(List<RestUserResolver> userResolvers) {
        this.userResolvers = userResolvers;
    }

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RestUser.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest((HttpServletRequest) webRequest.getNativeRequest());
        if (GeneralUtils.isNotEmpty(userResolvers)) {
            for (RestUserResolver userResolver : userResolvers) {
                try {
                    if (userResolver.supports(parameter)) {
                        RestUserInfo<?> restUser = userResolver.resolveUser(parameter, httpRequest);
                        RestUserInfoHolder.setUser(restUser);
                        return restUser;
                    }
                } catch (RestException exception) {
                    log.error("the user resolver can not resolve parameter, parameter: {}, error: {}", parameter,exception.getMessage());
                }
            }
        } else {
            log.warn("the user resolver can not found 'UserAdvice'");
        }
        return null;
    }
}
