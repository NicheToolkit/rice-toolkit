package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rice.TokenContext;
import io.github.nichetoolkit.rice.stereotype.RestUser;
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
     * <code>TOKEN_CONTEXT_KEY</code>
     * {@link java.lang.String} <p>The constant <code>TOKEN_CONTEXT_KEY</code> field.</p>
     * @see java.lang.String
     */
    public static final String TOKEN_CONTEXT_KEY = "TOKEN_CONTEXT_KEY";

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        /* 加了RestUser注解的不做处理 */
        return parameter.getParameterType().equals(TokenContext.class) && !parameter.hasParameterAnnotation(RestUser.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object object = webRequest.getAttribute(TOKEN_CONTEXT_KEY, RequestAttributes.SCOPE_REQUEST);
        if (!(object instanceof TokenContext)) {
            /* 创建RestMap实例并加入到请求中 */
            object = new TokenContext();
            webRequest.setAttribute(TOKEN_CONTEXT_KEY, object, RequestAttributes.SCOPE_REQUEST);
        }
        return object;
    }
}
