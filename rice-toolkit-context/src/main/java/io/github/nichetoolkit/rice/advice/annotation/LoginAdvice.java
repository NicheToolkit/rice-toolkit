package io.github.nichetoolkit.rice.advice.annotation;

import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.constant.LoginConstants;
import io.github.nichetoolkit.rice.error.TokenPermissionException;
import io.github.nichetoolkit.rice.helper.HttpRequestHelper;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <code>LoginAdvice</code>
 * <p>The type login advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface LoginAdvice {

    /**
     * <code>doAuthHandle</code>
     * <p>the auth handle method.</p>
     * @param request    {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request parameter is <code>RestHttpRequest</code> type.</p>
     * @param body       {@link java.lang.Object} <p>the body parameter is <code>Object</code> type.</p>
     * @param returnType {@link org.springframework.core.MethodParameter} <p>the return type parameter is <code>MethodParameter</code> type.</p>
     * @param restMap    {@link io.github.nichetoolkit.rice.RestMap} <p>the rest map parameter is <code>RestMap</code> type.</p>
     * @return {@link java.lang.Object} <p>the auth handle return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.Object
     * @see org.springframework.core.MethodParameter
     * @see io.github.nichetoolkit.rice.RestMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    default Object doAuthHandle(RestHttpRequest request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    /**
     * <code>doPendingHandle</code>
     * <p>the pending handle method.</p>
     * @param request    {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request parameter is <code>RestHttpRequest</code> type.</p>
     * @param body       {@link java.lang.Object} <p>the body parameter is <code>Object</code> type.</p>
     * @param returnType {@link org.springframework.core.MethodParameter} <p>the return type parameter is <code>MethodParameter</code> type.</p>
     * @param restMap    {@link io.github.nichetoolkit.rice.RestMap} <p>the rest map parameter is <code>RestMap</code> type.</p>
     * @return {@link java.lang.Object} <p>the pending handle return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.Object
     * @see org.springframework.core.MethodParameter
     * @see io.github.nichetoolkit.rice.RestMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    default Object doPendingHandle(RestHttpRequest request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    /**
     * <code>doLoginHandle</code>
     * <p>the login handle method.</p>
     * @param request    {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request parameter is <code>RestHttpRequest</code> type.</p>
     * @param body       {@link java.lang.Object} <p>the body parameter is <code>Object</code> type.</p>
     * @param returnType {@link org.springframework.core.MethodParameter} <p>the return type parameter is <code>MethodParameter</code> type.</p>
     * @param restMap    {@link io.github.nichetoolkit.rice.RestMap} <p>the rest map parameter is <code>RestMap</code> type.</p>
     * @return {@link java.lang.Object} <p>the login handle return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.Object
     * @see org.springframework.core.MethodParameter
     * @see io.github.nichetoolkit.rice.RestMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    default Object doLoginHandle(RestHttpRequest request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    /**
     * <code>doLogoutHandle</code>
     * <p>the logout handle method.</p>
     * @param request    {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request parameter is <code>RestHttpRequest</code> type.</p>
     * @param body       {@link java.lang.Object} <p>the body parameter is <code>Object</code> type.</p>
     * @param returnType {@link org.springframework.core.MethodParameter} <p>the return type parameter is <code>MethodParameter</code> type.</p>
     * @param restMap    {@link io.github.nichetoolkit.rice.RestMap} <p>the rest map parameter is <code>RestMap</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.Object
     * @see org.springframework.core.MethodParameter
     * @see io.github.nichetoolkit.rice.RestMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void doLogoutHandle(RestHttpRequest request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
    }


    /**
     * <code>preHandle</code>
     * <p>the handle method.</p>
     * @param request       {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request parameter is <code>RestHttpRequest</code> type.</p>
     * @param response      {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>the handler method parameter is <code>HandlerMethod</code> type.</p>
     * @return boolean <p>the handle return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see javax.servlet.http.HttpServletResponse
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    default boolean preHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        return true;
    }


    /**
     * <code>afterHandle</code>
     * <p>the handle method.</p>
     * @param request    {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request parameter is <code>RestHttpRequest</code> type.</p>
     * @param body       {@link java.lang.Object} <p>the body parameter is <code>Object</code> type.</p>
     * @param returnType {@link org.springframework.core.MethodParameter} <p>the return type parameter is <code>MethodParameter</code> type.</p>
     * @param restMap    {@link io.github.nichetoolkit.rice.RestMap} <p>the rest map parameter is <code>RestMap</code> type.</p>
     * @return {@link java.lang.Object} <p>the handle return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.Object
     * @see org.springframework.core.MethodParameter
     * @see io.github.nichetoolkit.rice.RestMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    default Object afterHandle(RestHttpRequest request, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        return null;
    }

    /**
     * <code>doResponseHandle</code>
     * <p>the response handle method.</p>
     * @param body {@link java.lang.Object} <p>the body parameter is <code>Object</code> type.</p>
     * @return boolean <p>the response handle return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    default boolean doResponseHandle(Object body) throws RestException {
        return (!(body instanceof RestResult)) || !((RestResult<?>) body).isSuccess();
    }

    /**
     * <code>doHeaderHandle</code>
     * <p>the header handle method.</p>
     * @param request      {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request parameter is <code>RestHttpRequest</code> type.</p>
     * @param headerTokens {@link java.util.List} <p>the header tokens parameter is <code>List</code> type.</p>
     * @return boolean <p>the header handle return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    default boolean doHeaderHandle(RestHttpRequest request, List<String> headerTokens) throws RestException {
        if (headerTokens.isEmpty()) {
            return true;
        }
        List<String> tokenList = HttpRequestHelper.resolveTokens(request, headerTokens, false);
        if (tokenList.isEmpty()) {
           throw new TokenPermissionException("the token permission denied");
        }
        return true;
    }

    /**
     * <code>isSkipApi</code>
     * <p>the skip api method.</p>
     * @param request       {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request parameter is <code>RestHttpRequest</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>the handler method parameter is <code>HandlerMethod</code> type.</p>
     * @return boolean <p>the skip api return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see org.springframework.web.method.HandlerMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    default boolean isSkipApi(RestHttpRequest request, HandlerMethod handlerMethod) throws RestException {
        String packageName = handlerMethod.getBean().getClass().getName();
        if (packageName.startsWith(LoginConstants.SKIP_API_PACKAGE)) {
            return true;
        }
        Object skipApiRequestFlag = request.getAttribute(LoginConstants.SKIP_API_REQUEST_FORWARD_FLAG);
        return skipApiRequestFlag != null;
    }
}
