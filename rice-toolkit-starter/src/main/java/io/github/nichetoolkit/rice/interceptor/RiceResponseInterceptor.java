package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.interceptor.advice.RiceLoginAdvice;
import io.github.nichetoolkit.rice.resolver.RiceMapArgumentResolver;
import io.github.nichetoolkit.rice.stereotype.RestCheck;
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
 * <p>LoginResponseInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Order(1)
@ControllerAdvice
public class RiceResponseInterceptor implements ResponseBodyAdvice {

    private final List<RiceLoginAdvice> loginInterceptors;

    @Autowired(required = false)
    public RiceResponseInterceptor() {
        this.loginInterceptors = new ArrayList<>();
    }

    @Autowired(required = false)
    public RiceResponseInterceptor(List<RiceLoginAdvice> loginInterceptors) {
        this.loginInterceptors = loginInterceptors;
    }


    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class converterType) {
        if (returnType.getMethod() == null) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType,@NonNull MediaType selectedContentType,@NonNull Class selectedConverterType,@NonNull ServerHttpRequest request,@NonNull ServerHttpResponse response) {
        RestMap restMap = null;
        RestRequestWrapper requestWrapper = null;
        /** 将请求中的模型数据容器取出来 */
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            requestWrapper = RestRequestHelper.getRestRequestWrapper(httpServletRequest);
            restMap = (RestMap) httpServletRequest.getAttribute(RiceMapArgumentResolver.REST_MAP_KEY);
        }
        if (GeneralUtils.isEmpty(restMap)) {
            restMap = new RestMap();
        }
        /** 登出接口注解 */
        RestLogout restLogout = AnnotationUtils.getAnnotation(returnType.getAnnotatedElement(), RestLogout.class);
        if (GeneralUtils.isEmpty(restLogout) && GeneralUtils.isNotEmpty(requestWrapper)) {
            restLogout = requestWrapper.getMethodAnnotation(RestLogout.class);
        }
        /** 登录接口注解 */
        RestLogin restLogin = AnnotationUtils.getAnnotation(returnType.getAnnotatedElement(), RestLogin.class);
        if (GeneralUtils.isEmpty(restLogin) && GeneralUtils.isNotEmpty(requestWrapper)) {
            restLogin = requestWrapper.getMethodAnnotation(RestLogin.class);
        }
        /** 预登录接口注解 */
        RestPending restPending = AnnotationUtils.getAnnotation(returnType.getAnnotatedElement(), RestPending.class);
        if (GeneralUtils.isEmpty(restPending) && GeneralUtils.isNotEmpty(requestWrapper)) {
            restPending = requestWrapper.getMethodAnnotation(RestPending.class);
        }
        /** 授权码接口注解 */
        RestAuth restAuth = AnnotationUtils.getAnnotation(returnType.getAnnotatedElement(), RestAuth.class);
        if (GeneralUtils.isEmpty(restAuth) && GeneralUtils.isNotEmpty(requestWrapper)) {
            restAuth = requestWrapper.getMethodAnnotation(RestAuth.class);
        }
        if (GeneralUtils.isNotEmpty(loginInterceptors)) {
            for (RiceLoginAdvice loginInterceptor : loginInterceptors) {
                try {
                    if (GeneralUtils.isNotEmpty(restLogout)) {
                        /** 登出接口拦截 */
                        loginInterceptor.logout(requestWrapper, body, returnType, restMap);
                        continue;
                    }
                    if (GeneralUtils.isNotEmpty(restLogin)) {
                        /** 登录接口拦截 */
                        Object loginResult = loginInterceptor.login(requestWrapper, body, returnType, restMap);
                        if (GeneralUtils.isNotEmpty(loginResult)) {
                            return loginResult;
                        }
                        continue;
                    }
                    if (GeneralUtils.isNotEmpty(restPending)) {
                        /** 预登录接口拦截 */
                        Object pendingResult = loginInterceptor.pending(requestWrapper, body, returnType, restMap);
                        if (GeneralUtils.isNotEmpty(pendingResult)) {
                            return pendingResult;
                        }
                        continue;
                    }
                    /** 拦截创建授权码接口一定要放在拦截登录接口后面 */
                    if (GeneralUtils.isNotEmpty(restAuth)) {
                        /** 授权码接口拦截 */
                        Object authResult = loginInterceptor.auth(requestWrapper, body, returnType, restMap);
                        if (GeneralUtils.isNotEmpty(authResult)) {
                            return authResult;
                        }
                        continue;
                    }
                    /** 拦截除了登录、登出、创建授权码接口之外的接口响应 */
                    Object handleResult = loginInterceptor.afterHandle(requestWrapper, body, returnType, restMap);
                    if (GeneralUtils.isNotEmpty(handleResult)) {
                        return handleResult;
                    }
                } catch (RestException exception) {
                   log.error("the login interceptor has error: {}",exception.getMessage());
                }
            }
        }

        return body;
    }
}
