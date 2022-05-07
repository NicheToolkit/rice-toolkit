package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import io.github.nichetoolkit.rice.error.service.ServiceUnauthorizedException;
import io.github.nichetoolkit.rice.error.token.TokenPrefixInvalidException;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import io.github.nichetoolkit.rice.interceptor.advice.RiceLoginAdvice;
import io.github.nichetoolkit.rice.stereotype.RestCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

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
@Order(10)
public class RiceLoginInterceptor implements RiceRequestInterceptor {
    private final RiceLoginProperties loginProperties;
    private final List<RiceLoginAdvice> loginInterceptors;

    @Autowired(required = false)
    public RiceLoginInterceptor(RiceLoginProperties loginProperties) {
        this.loginProperties = loginProperties;
        this.loginInterceptors = new ArrayList<>();
    }

    @Autowired(required = false)
    public RiceLoginInterceptor(RiceLoginProperties loginProperties, List<RiceLoginAdvice> loginInterceptors) {
        this.loginProperties = loginProperties;
        this.loginInterceptors = loginInterceptors;
    }

    @Override
    public void afterHandle(HttpServletRequest request, HttpServletResponse response,HandlerMethod handlerMethod) throws RestException {
        RestCheck restCheck = handlerMethod.getMethodAnnotation(RestCheck.class);
        RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
        /** 校验token前缀 */
        checkTokePrefix(restCheck, requestWrapper);
        /** 使用自定义拦截器进行校验，默认进行拦截 */
        boolean isAllow = false;
        if (GeneralUtils.isNotEmpty(loginInterceptors)) {
            for (RiceLoginAdvice loginInterceptor : loginInterceptors) {
                if (!loginInterceptor.preHandle(requestWrapper, response, handlerMethod)) {
                    /** 只要有一个拦截器不通过就直接拦截 */
                    isAllow = false;
                    break;
                } else {
                    isAllow = true;
                }
            }
        }
        if (!isAllow) {
            throw new ServiceUnauthorizedException();
        }
    }
    
    private void checkTokePrefix(RestCheck restCheck, RestRequestWrapper requestWrapper) throws TokenPrefixInvalidException {
        List<String> headerTokens = loginProperties.getHeaderTokens();
        if (headerTokens.isEmpty()) {
            return;
        }
        List<String> tokenList = InterceptorHelper.getHeaderToken(requestWrapper, headerTokens,false);
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
        } else {
            loginProperties.setTokenPrefixes(prefixList);
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
}
