package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.advice.LoginAdvice;
import io.github.nichetoolkit.rice.resolver.DefaultMapArgumentResolver;
import io.github.nichetoolkit.rice.stereotype.login.RestAuth;
import io.github.nichetoolkit.rice.stereotype.login.RestLogin;
import io.github.nichetoolkit.rice.stereotype.login.RestLogout;
import io.github.nichetoolkit.rice.stereotype.login.RestPending;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>DefaultResponseInterceptor</code>
 * <p>The type default response interceptor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.core.annotation.Order
 * @see org.springframework.web.bind.annotation.ControllerAdvice
 * @since Jdk1.8
 */
@Slf4j
@Order(1)
@ControllerAdvice
public class DefaultResponseInterceptor implements ResponseBodyAdvice<Object> {

    private final List<LoginAdvice> loginAdvices;

    /**
     * <code>DefaultResponseInterceptor</code>
     * Instantiates a new default response interceptor.
     */
    public DefaultResponseInterceptor() {
        this.loginAdvices = new ArrayList<>();
    }

    /**
     * <code>DefaultResponseInterceptor</code>
     * Instantiates a new default response interceptor.
     * @param loginAdvices {@link java.util.List} <p>the login advices parameter is <code>List</code> type.</p>
     * @see java.util.List
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultResponseInterceptor(List<LoginAdvice> loginAdvices) {
        this.loginAdvices = loginAdvices;
    }

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class converterType) {
        return returnType.getMethod() != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType,@NonNull MediaType selectedContentType,@NonNull Class selectedConverterType,@NonNull ServerHttpRequest request,@NonNull ServerHttpResponse response) {
        RestMap restMap = null;
        RestHttpRequest httpRequest = null;
        /* 将请求中的模型数据容器取出来 */
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            httpRequest = RestHttpRequest.getHttpRequest(httpServletRequest);
            restMap = (RestMap) httpServletRequest.getAttribute(DefaultMapArgumentResolver.REST_MAP_KEY);
        }
        if (GeneralUtils.isEmpty(restMap)) {
            restMap = new RestMap();
        }
        /* 登出接口注解 */
        RestLogout restLogout = AnnotationUtils.getAnnotation(returnType.getAnnotatedElement(), RestLogout.class);
        if (GeneralUtils.isEmpty(restLogout) && GeneralUtils.isNotEmpty(httpRequest)) {
            restLogout = httpRequest.getMethodAnnotation(RestLogout.class);
        }
        /* 登录接口注解 */
        RestLogin restLogin = AnnotationUtils.getAnnotation(returnType.getAnnotatedElement(), RestLogin.class);
        if (GeneralUtils.isEmpty(restLogin) && GeneralUtils.isNotEmpty(httpRequest)) {
            restLogin = httpRequest.getMethodAnnotation(RestLogin.class);
        }
        /* 预登录接口注解 */
        RestPending restPending = AnnotationUtils.getAnnotation(returnType.getAnnotatedElement(), RestPending.class);
        if (GeneralUtils.isEmpty(restPending) && GeneralUtils.isNotEmpty(httpRequest)) {
            restPending = httpRequest.getMethodAnnotation(RestPending.class);
        }
        /* 授权码接口注解 */
        RestAuth restAuth = AnnotationUtils.getAnnotation(returnType.getAnnotatedElement(), RestAuth.class);
        if (GeneralUtils.isEmpty(restAuth) && GeneralUtils.isNotEmpty(httpRequest)) {
            restAuth = httpRequest.getMethodAnnotation(RestAuth.class);
        }
        if (GeneralUtils.isNotEmpty(loginAdvices)) {
            for (LoginAdvice loginAdvice : loginAdvices) {
                try {
                    if (GeneralUtils.isNotEmpty(restLogout)) {
                        /* 登出接口拦截 */
                        loginAdvice.doLogoutHandle(httpRequest, body, returnType, restMap);
                        continue;
                    }
                    if (GeneralUtils.isNotEmpty(restLogin)) {
                        /* 登录接口拦截 */
                        Object loginResult = loginAdvice.doLoginHandle(httpRequest, body, returnType, restMap);
                        if (GeneralUtils.isNotEmpty(loginResult)) {
                            return loginResult;
                        }
                        continue;
                    }
                    if (GeneralUtils.isNotEmpty(restPending)) {
                        /* 预登录接口拦截 */
                        Object pendingResult = loginAdvice.doPendingHandle(httpRequest, body, returnType, restMap);
                        if (GeneralUtils.isNotEmpty(pendingResult)) {
                            return pendingResult;
                        }
                        continue;
                    }
                    /* 拦截创建授权码接口一定要放在拦截登录接口后面 */
                    if (GeneralUtils.isNotEmpty(restAuth)) {
                        /* 授权码接口拦截 */
                        Object authResult = loginAdvice.doAuthHandle(httpRequest, body, returnType, restMap);
                        if (GeneralUtils.isNotEmpty(authResult)) {
                            return authResult;
                        }
                        continue;
                    }
                    /* 拦截除了登录、登出、创建授权码接口之外的接口响应 */
                    Object handleResult = loginAdvice.afterHandle(httpRequest, body, returnType, restMap);
                    if (GeneralUtils.isNotEmpty(handleResult)) {
                        return handleResult;
                    }
                } catch (RestException exception) {
                   log.error("the login interceptor has error: {}", exception.getMessage());
                }
            }
        }

        return body;
    }
}
