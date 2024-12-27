package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import io.github.nichetoolkit.rice.constant.AdviceConstants;
import io.github.nichetoolkit.rice.error.service.ServiceUnauthorizedException;
import io.github.nichetoolkit.rice.error.TokenPrefixInvalidException;
import io.github.nichetoolkit.rice.advice.LoginAdvice;
import io.github.nichetoolkit.rice.helper.HttpRequestHelper;
import io.github.nichetoolkit.rice.stereotype.RestCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <code>DefaultLoginInterceptor</code>
 * <p>The default login interceptor class.</p>
 * @see  io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.stereotype.Component
 * @see  org.springframework.core.annotation.Order
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@Component
@Order(AdviceConstants.LOGIN_ORDER)
public class DefaultLoginInterceptor implements RequestHandleInterceptor {
    /**
     * <code>loginProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The <code>loginProperties</code> field.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    private final RiceLoginProperties loginProperties;
    /**
     * <code>loginAdvices</code>
     * {@link java.util.List} <p>The <code>loginAdvices</code> field.</p>
     * @see  java.util.List
     */
    private final List<LoginAdvice> loginAdvices;

    /**
     * <code>DefaultLoginInterceptor</code>
     * <p>Instantiates a new default login interceptor.</p>
     * @param loginProperties {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultLoginInterceptor(RiceLoginProperties loginProperties) {
        this.loginProperties = loginProperties;
        this.loginAdvices = new ArrayList<>();
    }

    /**
     * <code>DefaultLoginInterceptor</code>
     * <p>Instantiates a new default login interceptor.</p>
     * @param loginProperties {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @param loginAdvices {@link java.util.List} <p>The login advices parameter is <code>List</code> type.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see  java.util.List
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultLoginInterceptor(RiceLoginProperties loginProperties, List<LoginAdvice> loginAdvices) {
        this.loginProperties = loginProperties;
        this.loginAdvices = loginAdvices;
    }

    @Override
    public void afterHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        RestCheck restCheck = AnnotationUtils.getAnnotation(handlerMethod.getMethod(), RestCheck.class);
        RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
        /* 校验token前缀 */
        checkTokenPrefix(restCheck, httpRequest);
        /* 使用自定义拦截器进行校验，默认进行拦截 */
        boolean isAllow = false;
        if (GeneralUtils.isNotEmpty(this.loginAdvices)) {
            for (LoginAdvice loginAdvice : this.loginAdvices) {
                if (!loginAdvice.preHandle(httpRequest, response, handlerMethod)) {
                    /* 只要有一个拦截器不通过就直接拦截 */
                    isAllow = false;
                    break;
                } else {
                    isAllow = true;
                }
            }
        } else {
            isAllow = true;
        }
        if (!isAllow) {
            throw new ServiceUnauthorizedException();
        }
    }

    /**
     * <code>checkTokenPrefix</code>
     * <p>The check token prefix method.</p>
     * @param restCheck {@link io.github.nichetoolkit.rice.stereotype.RestCheck} <p>The rest check parameter is <code>RestCheck</code> type.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @see  io.github.nichetoolkit.rice.stereotype.RestCheck
     * @see  io.github.nichetoolkit.rest.RestHttpRequest
     * @see  io.github.nichetoolkit.rice.error.TokenPrefixInvalidException
     * @throws TokenPrefixInvalidException {@link io.github.nichetoolkit.rice.error.TokenPrefixInvalidException} <p>The token prefix invalid exception is <code>TokenPrefixInvalidException</code> type.</p>
     */
    private void checkTokenPrefix(RestCheck restCheck, RestHttpRequest httpRequest) throws TokenPrefixInvalidException {
        List<String> tokenHeaders = loginProperties.getTokenHeaders();
        if (tokenHeaders.isEmpty()) {
            return;
        }
        List<String> tokenList = HttpRequestHelper.resolveTokens(httpRequest, tokenHeaders,false);
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
        /* 没有配置token前缀，不进行校验 */
        if (GeneralUtils.isEmpty(prefixList) ) {
            return;
        } else {
            loginProperties.setTokenPrefixes(prefixList);
        }
        /* 遍历获取到的token和配置的token前缀，只要有一个token是以配置的前缀开头，则放行 */
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
