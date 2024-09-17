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

/**
 * <code>RiceInterceptorAutoConfigure</code>
 * <p>The type rice interceptor auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rice.login.enabled", havingValue = "true",matchIfMissing = true)
public class RiceInterceptorAutoConfigure implements WebMvcConfigurer {

    private final DefaultUserArgumentResolver userArgumentResolver;
    private final List<RequestHandleInterceptor> handleInterceptors;
    private final RiceLoginProperties loginProperties;

    /**
     * <code>RiceInterceptorAutoConfigure</code>
     * Instantiates a new rice interceptor auto configure.
     * @param userArgumentResolver {@link io.github.nichetoolkit.rice.resolver.DefaultUserArgumentResolver} <p>the user argument resolver parameter is <code>DefaultUserArgumentResolver</code> type.</p>
     * @param loginProperties      {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>the login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @see io.github.nichetoolkit.rice.resolver.DefaultUserArgumentResolver
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public RiceInterceptorAutoConfigure(DefaultUserArgumentResolver userArgumentResolver, RiceLoginProperties loginProperties) {
        this.userArgumentResolver = userArgumentResolver;
        this.handleInterceptors = new ArrayList<>();
        this.loginProperties = loginProperties;
    }

    /**
     * <code>RiceInterceptorAutoConfigure</code>
     * Instantiates a new rice interceptor auto configure.
     * @param userArgumentResolver {@link io.github.nichetoolkit.rice.resolver.DefaultUserArgumentResolver} <p>the user argument resolver parameter is <code>DefaultUserArgumentResolver</code> type.</p>
     * @param requestInterceptors  {@link java.util.List} <p>the request interceptors parameter is <code>List</code> type.</p>
     * @param loginProperties      {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>the login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @see io.github.nichetoolkit.rice.resolver.DefaultUserArgumentResolver
     * @see java.util.List
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public RiceInterceptorAutoConfigure(DefaultUserArgumentResolver userArgumentResolver, List<RequestHandleInterceptor> requestInterceptors, RiceLoginProperties loginProperties) {
        this.userArgumentResolver = userArgumentResolver;
        this.handleInterceptors = requestInterceptors;
        this.loginProperties = loginProperties;
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

    /**
     * <code>loggingKey</code>
     * <p>the key method.</p>
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestLoggingKey} <p>the key return object is <code>RestLoggingKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see io.github.nichetoolkit.rest.RestLoggingKey
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     */
    @Bean
    @Primary
    public RestLoggingKey loggingKey(RestLogbackProperties logbackProperties) {
        return new DefaultLoggingKeyResolver(logbackProperties, loginProperties);
    }

}
