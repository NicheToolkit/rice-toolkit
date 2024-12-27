package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.RestLoggingKey;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor;
import io.github.nichetoolkit.rice.defaults.DefaultLoggingKeyResolver;
import io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver;
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
 * <code>RiceLoginAutoConfigure</code>
 * <p>The rice login auto configure class.</p>
 * @see  org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.boot.autoconfigure.AutoConfiguration
 * @see  java.lang.SuppressWarnings
 * @see  org.springframework.context.annotation.ComponentScan
 * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rice.login.enabled", havingValue = "true",matchIfMissing = true)
public class RiceLoginAutoConfigure implements WebMvcConfigurer {

    /**
     * <code>userArgumentResolver</code>
     * {@link io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver} <p>The <code>userArgumentResolver</code> field.</p>
     * @see  io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver
     */
    private final DefaultUserInfoResolver userArgumentResolver;
    /**
     * <code>handleInterceptors</code>
     * {@link java.util.List} <p>The <code>handleInterceptors</code> field.</p>
     * @see  java.util.List
     */
    private final List<RequestHandleInterceptor> handleInterceptors;
    /**
     * <code>loginProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The <code>loginProperties</code> field.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    private final RiceLoginProperties loginProperties;

    /**
     * <code>RiceLoginAutoConfigure</code>
     * <p>Instantiates a new rice login auto configure.</p>
     * @param userArgumentResolver {@link io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver} <p>The user argument resolver parameter is <code>DefaultUserInfoResolver</code> type.</p>
     * @param loginProperties {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver
     * @see  io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public RiceLoginAutoConfigure(DefaultUserInfoResolver userArgumentResolver, RiceLoginProperties loginProperties) {
        this.userArgumentResolver = userArgumentResolver;
        this.handleInterceptors = new ArrayList<>();
        this.loginProperties = loginProperties;
    }

    /**
     * <code>RiceLoginAutoConfigure</code>
     * <p>Instantiates a new rice login auto configure.</p>
     * @param userArgumentResolver {@link io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver} <p>The user argument resolver parameter is <code>DefaultUserInfoResolver</code> type.</p>
     * @param requestInterceptors {@link java.util.List} <p>The request interceptors parameter is <code>List</code> type.</p>
     * @param loginProperties {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public RiceLoginAutoConfigure(DefaultUserInfoResolver userArgumentResolver, List<RequestHandleInterceptor> requestInterceptors, RiceLoginProperties loginProperties) {
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
     * <p>The logging key method.</p>
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see  io.github.nichetoolkit.rest.RestLoggingKey
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.context.annotation.Primary
     * @return  {@link io.github.nichetoolkit.rest.RestLoggingKey} <p>The logging key return object is <code>RestLoggingKey</code> type.</p>
     */
    @Bean
    @Primary
    public RestLoggingKey loggingKey(RestLogbackProperties logbackProperties) {
        return new DefaultLoggingKeyResolver(logbackProperties, loginProperties);
    }

}
