package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.RestUser;
import org.springframework.core.MethodParameter;

/**
 * <p>RestUserAdvice 自定义登录用户信息参数解析器</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 * {@link RestUser}
 */
public interface RiceUserAdvice {
    /**
     * 是否支持指定参数的解析，默认支持
     * @param parameter 参数对象
     * @return true 支持，false 不支持
     */
    default boolean supports(MethodParameter parameter) {
        RestUser restUser = parameter.getParameterAnnotation(RestUser.class);
        return GeneralUtils.isNotEmpty(restUser);
    }

    /**
     * 解析当前登录用户信息
     * 只有{@link RiceUserAdvice#supports(MethodParameter)}方法返回true才会执行此方法
     * @param parameter 标注了{@link RestUser}注解的参数对象，通过参数对象可以获得到注解实例
     * @param requestWrapper {@link javax.servlet.http.HttpServletRequest}包装类
     * @return 登录用户信息
     * @throws RestException RestException
     */
    io.github.nichetoolkit.rice.RestUser resolveArgument(MethodParameter parameter, RestRequestWrapper requestWrapper) throws RestException;
}
