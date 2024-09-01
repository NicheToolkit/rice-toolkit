package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.RestLoggingKey;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor;
import io.github.nichetoolkit.rice.resolver.DefaultLoggingKeyResolver;
import io.github.nichetoolkit.rice.resolver.DefaultUserArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rice.login.enabled", havingValue = "true",matchIfMissing = true)
public class RiceInterceptorAutoConfigure implements WebMvcConfigurer {

    private final DefaultUserArgumentResolver userArgumentResolver;
    private final List<RequestHandleInterceptor> handleInterceptors;
    private final RiceLoginProperties loginProperties;

    @Autowired(required = false)
    public RiceInterceptorAutoConfigure(DefaultUserArgumentResolver userArgumentResolver, RiceLoginProperties loginProperties) {
        this.userArgumentResolver = userArgumentResolver;
        this.handleInterceptors = new ArrayList<>();
        this.loginProperties = loginProperties;
    }

    @Autowired(required = false)
    public RiceInterceptorAutoConfigure(DefaultUserArgumentResolver userArgumentResolver, List<RequestHandleInterceptor> requestInterceptors, RiceLoginProperties loginProperties) {
        this.userArgumentResolver = userArgumentResolver;
        this.handleInterceptors = requestInterceptors;
        this.loginProperties = loginProperties;
        log.debug("request interceptors size: {}",requestInterceptors.size());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        List<String> skipUrls = loginProperties.getSkipUrls();
        if (GeneralUtils.isNotEmpty(handleInterceptors)) {
            for (RequestHandleInterceptor handleInterceptor : handleInterceptors) {
                InterceptorRegistration registration = registry.addInterceptor(handleInterceptor);
                if (GeneralUtils.isNotEmpty(skipUrls)) {
                    for (String url : skipUrls) {
                        registration.excludePathPatterns(url);
                    }
                }
            }
        }
    }

    @Bean
    @Primary
    public RestLoggingKey loggingKey(RestLogbackProperties logbackProperties) {
        return new DefaultLoggingKeyResolver(logbackProperties, loginProperties);
    }

}
