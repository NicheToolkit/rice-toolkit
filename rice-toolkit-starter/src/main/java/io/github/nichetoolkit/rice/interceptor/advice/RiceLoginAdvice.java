package io.github.nichetoolkit.rice.interceptor.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.constant.LoginConstants;
import io.github.nichetoolkit.rice.error.token.TokenNoPermissionException;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RiceLoginAdvice {

    default Object auth(RestRequestWrapper request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
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


    default boolean preHandle(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        return true;
    }


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
        List<String> tokenList = InterceptorHelper.getHeaderToken(request, headerTokens,false);
        if (tokenList.isEmpty()) {
           throw new TokenNoPermissionException("访问令牌无权限！");
        }
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
