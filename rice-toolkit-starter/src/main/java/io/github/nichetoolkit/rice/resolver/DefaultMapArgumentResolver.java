package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.stereotype.login.RestUser;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * <code>DefaultMapArgumentResolver</code>
 * <p>The type default map argument resolver class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
 */
@Component
public class DefaultMapArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * <code>REST_MAP_KEY</code>
     * {@link java.lang.String} <p>the constant <code>REST_MAP_KEY</code> field.</p>
     * @see java.lang.String
     */
    public static final String REST_MAP_KEY = "REST_MAP_KEY";

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        /* 加了RestUser注解的不做处理 */
        return parameter.getParameterType().equals(RestMap.class) && !parameter.hasParameterAnnotation(RestUser.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object object = webRequest.getAttribute(REST_MAP_KEY, RequestAttributes.SCOPE_REQUEST);
        if (!(object instanceof RestMap)) {
            /* 创建RestMap实例并加入到请求中 */
            object = new RestMap();
            webRequest.setAttribute(REST_MAP_KEY, object, RequestAttributes.SCOPE_REQUEST);
        }
        return object;
    }
}
