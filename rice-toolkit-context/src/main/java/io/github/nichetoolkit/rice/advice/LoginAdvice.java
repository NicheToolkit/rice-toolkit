package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.constant.LoginConstants;
import io.github.nichetoolkit.rice.error.TokenPermissionException;
import io.github.nichetoolkit.rice.helper.PurviewHelper;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface LoginAdvice {

    default Object doAuthHandle(RestHttpRequest request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    default Object doPendingHandle(RestHttpRequest request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    default Object doLoginHandle(RestHttpRequest request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    default void doLogoutHandle(RestHttpRequest request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
    }


    default boolean preHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        return true;
    }


    default Object afterHandle(RestHttpRequest request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    default boolean doResponseHandle(Object body) throws RestException {
        return (!(body instanceof RestResult)) || !((RestResult<?>) body).isSuccess();
    }

    default boolean doHeaderHandle(RestHttpRequest request, List<String> headerTokens) throws RestException {
        if (headerTokens.isEmpty()) {
            return true;
        }
        List<String> tokenList = PurviewHelper.resolveToken(request, headerTokens, false);
        if (tokenList.isEmpty()) {
           throw new TokenPermissionException("the token permission denied");
        }
        return true;
    }

    default boolean isSkipApi(RestHttpRequest request, HandlerMethod handlerMethod) throws RestException {
        String packageName = handlerMethod.getBean().getClass().getName();
        if (packageName.startsWith(LoginConstants.SKIP_API_PACKAGE)) {
            return true;
        }
        Object skipApiRequestFlag = request.getAttribute(LoginConstants.SKIP_API_REQUEST_FORWARD_FLAG);
        return skipApiRequestFlag != null;
    }
}
