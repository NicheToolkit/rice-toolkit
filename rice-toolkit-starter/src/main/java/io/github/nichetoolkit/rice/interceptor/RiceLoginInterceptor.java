package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.constant.LoginConstants;
import io.github.nichetoolkit.rice.error.login.NoPermissionException;
import io.github.nichetoolkit.rice.helper.LoginTokenHelper;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>RiceLoginInterceptor 自定义登录用户信息参数解析器</p>
 * 登录模块自定义拦截器接口，实现类需要以注解或者其它形式将自己加到到Spring容器中
 * 支持拦截需要进行登录校验的每个请求
 * 支持拦截登录、登出接口响应，修改登录接口响应报文，登出接口响应不支持修改
 * 支持多个拦截器 只支持拦截Spring MVC管理的接口
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0

 */
@SuppressWarnings({"unused"})
public interface RiceLoginInterceptor {

    default Object authValue(RestRequestWrapper request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    default Object pending(RestRequestWrapper request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    default Object login(RestRequestWrapper request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    default void logout(RestRequestWrapper request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
    }


    default boolean checkAccessAuth(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        return true;
    }

    default boolean checkAuthValue(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        return true;
    }

    boolean preHandle(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException;


    default Object afterHandle(RestRequestWrapper request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    default boolean checkResponse(Object body) throws RestException {
        return (!(body instanceof RestResult)) || !((RestResult) body).isSuccess();
    }

    default boolean checkHeader(RestRequestWrapper request, List<String> headerTokens) throws RestException {
        if (headerTokens.isEmpty()) {
            return true;
        }
        List<String> tokenList = LoginTokenHelper.getHeaderToken(request, headerTokens);
        if (tokenList.isEmpty()) {
           throw new NoPermissionException();
        }
        //todo 后面可以在这里校验token是否存在redis中
        return true;
    }

    default boolean isSkipApi(RestRequestWrapper request, HandlerMethod handlerMethod) throws RestException {
        String packageName = handlerMethod.getBean().getClass().getName();
        if (packageName.startsWith(LoginConstants.SKIP_API_PACKAGE)) {
            return true;
        }
        Object skipApiRequestFlag = request.getAttribute(LoginConstants.SKIP_API_REQUEST_FORWARD_FLAG);
        return skipApiRequestFlag != null;
    }
}
