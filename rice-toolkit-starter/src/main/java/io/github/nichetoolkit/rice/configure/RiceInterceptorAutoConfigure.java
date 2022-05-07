package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.interceptor.RiceRequestInterceptor;
import io.github.nichetoolkit.rice.interceptor.advice.RiceAccessAdvice;
import io.github.nichetoolkit.rice.resolver.RiceUserArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>RestIdentityAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rice.login.enabled", havingValue = "true")
public class RiceInterceptorAutoConfigure implements WebMvcConfigurer {

    private final RiceUserArgumentResolver userArgumentResolver;
    private final List<RiceRequestInterceptor> requestInterceptors;
    private final RiceLoginProperties loginProperties;

    @Autowired(required = false)
    public RiceInterceptorAutoConfigure(RiceUserArgumentResolver userArgumentResolver,  RiceLoginProperties loginProperties) {
        this.userArgumentResolver = userArgumentResolver;
        this.requestInterceptors = new ArrayList<>();
        this.loginProperties = loginProperties;
    }

    @Autowired(required = false)
    public RiceInterceptorAutoConfigure(RiceUserArgumentResolver userArgumentResolver, List<RiceRequestInterceptor> requestInterceptors, RiceLoginProperties loginProperties) {
        this.userArgumentResolver = userArgumentResolver;
        this.requestInterceptors = requestInterceptors;
        this.loginProperties = loginProperties;
        log.debug("request interceptors size: {}",requestInterceptors.size());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> skipUrls = loginProperties.getSkipUrls();
        if (GeneralUtils.isNotEmpty(requestInterceptors)) {
            for (RiceRequestInterceptor requestInterceptor : requestInterceptors) {
                InterceptorRegistration registration = registry.addInterceptor(requestInterceptor);
                if (GeneralUtils.isNotEmpty(skipUrls)) {
                    for (String url : skipUrls) {
                        registration.excludePathPatterns(url);
                    }
                }
            }
        }




    }

}
