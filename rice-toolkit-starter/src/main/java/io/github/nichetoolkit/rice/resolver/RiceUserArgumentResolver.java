package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RiceUserAdvice;
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
import java.util.List;

/**
 * <p>RestUserArgumentResolver 登录用户信息参数解析</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
public class RiceUserArgumentResolver implements HandlerMethodArgumentResolver {

    private List<RiceUserAdvice> userAdvices;

    @Autowired(required = false)
    public RiceUserArgumentResolver(List<RiceUserAdvice> userAdvices) {
        this.userAdvices = userAdvices;
    }

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RestUser.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper((HttpServletRequest) webRequest.getNativeRequest());
        if (GeneralUtils.isNotEmpty(userAdvices)) {
            for (RiceUserAdvice userAdvice : userAdvices) {
                try {
                    if (userAdvice.supports(parameter)) {
                        return userAdvice.resolveArgument(parameter, requestWrapper);
                    }
                } catch (RestException exception) {
                    log.error("the user resolver can not resolve parameter, parameter: {}, error", parameter,exception.getMessage());
                }
            }
        } else {
            log.warn("the user resolver can not found 'RestUserAdvice'");
        }
        return null;
    }
}
