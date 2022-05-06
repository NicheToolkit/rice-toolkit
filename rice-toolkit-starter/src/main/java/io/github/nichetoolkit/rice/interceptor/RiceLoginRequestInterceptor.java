package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import io.github.nichetoolkit.rice.error.token.TokenPrefixInvalidException;
import io.github.nichetoolkit.rice.helper.LoginTokenHelper;
import io.github.nichetoolkit.rice.stereotype.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>RiceLoginInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
public class RiceLoginRequestInterceptor implements AsyncHandlerInterceptor {
    private final RiceLoginProperties loginProperties;
    private final List<RiceLoginInterceptor> loginInterceptors;

    @Autowired(required = false)
    public RiceLoginRequestInterceptor(RiceLoginProperties loginProperties) {
        this.loginProperties = loginProperties;
        this.loginInterceptors = new ArrayList<>();
    }

    @Autowired(required = false)
    public RiceLoginRequestInterceptor( RiceLoginProperties loginProperties,List<RiceLoginInterceptor> loginInterceptors) {
        this.loginProperties = loginProperties;
        this.loginInterceptors = loginInterceptors;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /** 不是接口调用不拦截 */
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        /** 接口不存在就不需要执行拦截逻辑 */
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> methodBeanType = handlerMethod.getBeanType();
        String packageName = handlerMethod.getBean().getClass().getName();
        String springbootError = "org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController";
        if (packageName.contains(springbootError)) {
            return false;
        }
        if (handlerMethod.hasMethodAnnotation(RestSkip.class)
                || GeneralUtils.isNotEmpty(methodBeanType.getAnnotation(RestSkip.class))) {
            return true;
        }
        /** 登录注解 */
        RestLogin restLogin = handlerMethod.getMethodAnnotation(RestLogin.class);
        /** 授权码注解 */
        RestAuth restAuth = handlerMethod.getMethodAnnotation(RestAuth.class);
        /** 如果登录注解和授权码注解都不为空，说明是使用授权码登录的接口，这里就需要调自定义的拦截器的检验授权码方法 */
        if (GeneralUtils.isNotEmpty(restLogin) && GeneralUtils.isNotEmpty(restAuth)) {
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(loginInterceptors)) {
                for (RiceLoginInterceptor loginInterceptor : loginInterceptors) {
                    if (!loginInterceptor.checkAuthValue(requestWrapper, response, handlerMethod)) {
                        /** 只要有一个拦截器不通过就直接拦截 */
                        handleResult(false, requestWrapper, response);
                        return false;
                    }
                }
            }

        }
        /** 模块权限注解 */
        RestModule restModule = handlerMethod.getMethodAnnotation(RestModule.class);
        if (GeneralUtils.isNotEmpty(restLogin) && restModule != null) {
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(loginInterceptors)) {
                for (RiceLoginInterceptor loginInterceptor : loginInterceptors) {
                    if (!loginInterceptor.checkModule(requestWrapper, response, handlerMethod)) {
                        /** 只要有一个拦截器不通过就直接拦截 */
                        handleResult(false, requestWrapper, response);
                        return false;
                    }
                }
            }
        }
        /** 权限注解  */
        RestPurview restPurview = handlerMethod.getMethodAnnotation(RestPurview.class);
        if (GeneralUtils.isNotEmpty(restLogin) && GeneralUtils.isEmpty(restModule) && restPurview != null) {
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(loginInterceptors)) {
                for (RiceLoginInterceptor loginInterceptor : loginInterceptors) {
                    if (!loginInterceptor.checkPurview(requestWrapper, response, handlerMethod)) {
                        /** 只要有一个拦截器不通过就直接拦截 */
                        handleResult(false, requestWrapper, response);
                        return false;
                    }
                }
            }
        }
        /** 授权访问注解 */
        RestAccess restAccess = handlerMethod.getMethodAnnotation(RestAccess.class);
        if (GeneralUtils.isNotEmpty(restLogin) && restAccess != null) {
            RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
            if (GeneralUtils.isNotEmpty(loginInterceptors)) {
                for (RiceLoginInterceptor loginInterceptor : loginInterceptors) {
                    if (!loginInterceptor.checkAccessAuth(requestWrapper, response, handlerMethod)) {
                        /** 只要有一个拦截器不通过就直接拦截 */
                        handleResult(false, requestWrapper, response);
                        return false;
                    }
                }
            }
        }
        /** 登录接口、创建授权码接口是开放的 */
        if (GeneralUtils.isNotEmpty(restLogin)) {
            return true;
        }
        RestCheck restCheck = handlerMethod.getMethodAnnotation(RestCheck.class);
        RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
        /** 校验token前缀 */
        checkTokePrefix(restCheck, requestWrapper);
        /** 使用自定义拦截器进行校验，默认进行拦截 */
        boolean isAllow = false;
        if (GeneralUtils.isNotEmpty(loginInterceptors)) {
            for (RiceLoginInterceptor loginInterceptor : loginInterceptors) {
                if (!loginInterceptor.preHandle(requestWrapper, response, handlerMethod)) {
                    /** 只要有一个拦截器不通过就直接拦截 */
                    isAllow = false;
                    break;
                } else {
                    isAllow = true;
                }
            }
        }
        handleResult(isAllow, requestWrapper, response);
        return isAllow;
    }
    
    private void checkTokePrefix(RestCheck restCheck, RestRequestWrapper requestWrapper) throws TokenPrefixInvalidException {
        List<String> headerTokens = loginProperties.getHeaderTokens();
        if (headerTokens.isEmpty()) {
            return;
        }
        List<String> tokenList = LoginTokenHelper.getHeaderToken(requestWrapper, headerTokens);
        if (tokenList.isEmpty()) {
            return;
        }
        List<String> prefixList = new ArrayList<>();
        List<String> tokenPrefixes = loginProperties.getTokenPrefixes();
        if (GeneralUtils.isNotEmpty(tokenPrefixes) ) {
            prefixList.addAll(tokenPrefixes);
        }
        if (GeneralUtils.isNotEmpty(restCheck) && GeneralUtils.isNotEmpty(restCheck.prefixes())) {
            prefixList.addAll(Arrays.asList(restCheck.prefixes()));
        }
        /** 没有配置token前缀，不进行校验 */
        if (GeneralUtils.isEmpty(prefixList) ) {
            return;
        }
        /** 遍历获取到的token和配置的token前缀，只要有一个token是以配置的前缀开头，则放行 */
        for (String token : tokenList) {
            for (String prefix : prefixList) {
                if (token.startsWith(prefix)) {
                    return;
                }
            }
        }
        throw new TokenPrefixInvalidException();
    }

    private void handleResult(boolean isHandle, RestRequestWrapper request, HttpServletResponse response) {
        if (!isHandle) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
