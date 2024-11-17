package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.TokenContext;
import io.github.nichetoolkit.rice.constant.LoginConstants;
import io.github.nichetoolkit.rice.error.TokenPermissionException;
import io.github.nichetoolkit.rice.helper.HttpRequestHelper;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <code>LoginAdvice</code>
 * <p>The login advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface LoginAdvice {

    /**
     * <code>doAuthHandle</code>
     * <p>The do auth handle method.</p>
     * @param request {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The request parameter is <code>RestHttpRequest</code> type.</p>
     * @param body {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param returnType {@link org.springframework.core.MethodParameter} <p>The return type parameter is <code>MethodParameter</code> type.</p>
     * @param context {@link io.github.nichetoolkit.rice.TokenContext} <p>The context parameter is <code>TokenContext</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestHttpRequest
     * @see  java.lang.Object
     * @see  org.springframework.core.MethodParameter
     * @see  io.github.nichetoolkit.rice.TokenContext
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.Object} <p>The do auth handle return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default Object doAuthHandle(RestHttpRequest request, Object body, MethodParameter returnType, TokenContext context) throws RestException {
        return null;
    }

    /**
     * <code>doPendingHandle</code>
     * <p>The do pending handle method.</p>
     * @param request {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The request parameter is <code>RestHttpRequest</code> type.</p>
     * @param body {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param returnType {@link org.springframework.core.MethodParameter} <p>The return type parameter is <code>MethodParameter</code> type.</p>
     * @param context {@link io.github.nichetoolkit.rice.TokenContext} <p>The context parameter is <code>TokenContext</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestHttpRequest
     * @see  java.lang.Object
     * @see  org.springframework.core.MethodParameter
     * @see  io.github.nichetoolkit.rice.TokenContext
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.Object} <p>The do pending handle return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default Object doPendingHandle(RestHttpRequest request, Object body, MethodParameter returnType, TokenContext context) throws RestException {
        return null;
    }

    /**
     * <code>doLoginHandle</code>
     * <p>The do login handle method.</p>
     * @param request {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The request parameter is <code>RestHttpRequest</code> type.</p>
     * @param body {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param returnType {@link org.springframework.core.MethodParameter} <p>The return type parameter is <code>MethodParameter</code> type.</p>
     * @param context {@link io.github.nichetoolkit.rice.TokenContext} <p>The context parameter is <code>TokenContext</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestHttpRequest
     * @see  java.lang.Object
     * @see  org.springframework.core.MethodParameter
     * @see  io.github.nichetoolkit.rice.TokenContext
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.Object} <p>The do login handle return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default Object doLoginHandle(RestHttpRequest request, Object body, MethodParameter returnType, TokenContext context) throws RestException {
        return null;
    }

    /**
     * <code>doLogoutHandle</code>
     * <p>The do logout handle method.</p>
     * @param request {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The request parameter is <code>RestHttpRequest</code> type.</p>
     * @param body {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param returnType {@link org.springframework.core.MethodParameter} <p>The return type parameter is <code>MethodParameter</code> type.</p>
     * @param context {@link io.github.nichetoolkit.rice.TokenContext} <p>The context parameter is <code>TokenContext</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestHttpRequest
     * @see  java.lang.Object
     * @see  org.springframework.core.MethodParameter
     * @see  io.github.nichetoolkit.rice.TokenContext
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default void doLogoutHandle(RestHttpRequest request, Object body, MethodParameter returnType, TokenContext context) throws RestException {
    }


    /**
     * <code>preHandle</code>
     * <p>The pre handle method.</p>
     * @param request {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The request parameter is <code>RestHttpRequest</code> type.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>The handler method parameter is <code>HandlerMethod</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestHttpRequest
     * @see  javax.servlet.http.HttpServletResponse
     * @see  org.springframework.web.method.HandlerMethod
     * @see  io.github.nichetoolkit.rest.RestException
     * @return boolean <p>The pre handle return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default boolean preHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        return true;
    }


    /**
     * <code>afterHandle</code>
     * <p>The after handle method.</p>
     * @param request {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The request parameter is <code>RestHttpRequest</code> type.</p>
     * @param body {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param returnType {@link org.springframework.core.MethodParameter} <p>The return type parameter is <code>MethodParameter</code> type.</p>
     * @param context {@link io.github.nichetoolkit.rice.TokenContext} <p>The context parameter is <code>TokenContext</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestHttpRequest
     * @see  java.lang.Object
     * @see  org.springframework.core.MethodParameter
     * @see  io.github.nichetoolkit.rice.TokenContext
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.Object} <p>The after handle return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default Object afterHandle(RestHttpRequest request, Object body, MethodParameter returnType, TokenContext context) throws RestException {
        return null;
    }

    /**
     * <code>doResponseHandle</code>
     * <p>The do response handle method.</p>
     * @param body {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @return boolean <p>The do response handle return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default boolean doResponseHandle(Object body) throws RestException {
        return (!(body instanceof RestResult)) || !((RestResult<?>) body).isSuccess();
    }

    /**
     * <code>doHeaderHandle</code>
     * <p>The do header handle method.</p>
     * @param request {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The request parameter is <code>RestHttpRequest</code> type.</p>
     * @param headerTokens {@link java.util.List} <p>The header tokens parameter is <code>List</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestHttpRequest
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return boolean <p>The do header handle return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    default boolean doHeaderHandle(RestHttpRequest request, List<String> headerTokens) throws RestException {
        if (headerTokens.isEmpty()) {
            return true;
        }
        List<String> tokenList = HttpRequestHelper.resolveTokens(request, headerTokens, false);
        OptionalUtils.ofEmpty(headerTokens,TokenPermissionException::new);
        return true;
    }

    /**
     * <code>isSkipApi</code>
     * <p>The is skip api method.</p>
     * @param request {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The request parameter is <code>RestHttpRequest</code> type.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>The handler method parameter is <code>HandlerMethod</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestHttpRequest
     * @see  org.springframework.web.method.HandlerMethod
     * @see  io.github.nichetoolkit.rest.RestException
     * @return boolean <p>The is skip api return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
