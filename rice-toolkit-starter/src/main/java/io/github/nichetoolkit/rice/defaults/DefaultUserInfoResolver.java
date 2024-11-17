package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestUserInfo;
import io.github.nichetoolkit.rice.RestUserResolver;
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

/**
 * <code>DefaultUserInfoResolver</code>
 * <p>The default user info resolver class.</p>
 * @see  org.springframework.web.method.support.HandlerMethodArgumentResolver
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.stereotype.Component
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@Component
public class DefaultUserInfoResolver implements HandlerMethodArgumentResolver {

    /**
     * <code>userResolvers</code>
     * {@link java.util.List} <p>The <code>userResolvers</code> field.</p>
     * @see  java.util.List
     */
    private final List<RestUserResolver> userResolvers;

    /**
     * <code>DefaultUserInfoResolver</code>
     * <p>Instantiates a new default user info resolver.</p>
     */
    public DefaultUserInfoResolver() {
        this.userResolvers = new ArrayList<>();
    }

    /**
     * <code>DefaultUserInfoResolver</code>
     * <p>Instantiates a new default user info resolver.</p>
     * @param userResolvers {@link java.util.List} <p>The user resolvers parameter is <code>List</code> type.</p>
     * @see  java.util.List
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultUserInfoResolver(List<RestUserResolver> userResolvers) {
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
                        RestUserInfo<?> userInfo = userResolver.resolveUser(parameter, httpRequest);
                        UserInfoHolder.setUser(userInfo);
                        return userInfo;
                    }
                } catch (RestException exception) {
                    log.error("the user resolver can not resolve parameter, parameter: {}, error: {}", parameter,exception.getMessage());
                }
            }
        } else {
            log.warn("the user resolver can not found");
        }
        return null;
    }
}
