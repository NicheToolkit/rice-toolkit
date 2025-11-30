package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver;
import io.github.nichetoolkit.rice.interceptor.RequestHandleInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.jspecify.annotations.NonNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <code>RiceWebMvcAutoConfigure</code>
 * <p>The rice web mvc auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @since Jdk17
 */
@AutoConfiguration
@AutoConfigureAfter(RiceLoginAutoConfigure.class)
@ConditionalOnProperty(value = "nichetoolkit.rice.login.enabled", havingValue = "true")
public class RiceWebMvcAutoConfigure implements WebMvcConfigurer {

    /**
     * <code>loginProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The <code>loginProperties</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    private final RiceLoginProperties loginProperties;
    /**
     * <code>userArgumentResolver</code>
     * {@link io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver} <p>The <code>userArgumentResolver</code> field.</p>
     * @see io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver
     */
    private final DefaultUserInfoResolver userArgumentResolver;
    /**
     * <code>handleInterceptors</code>
     * {@link java.util.List} <p>The <code>handleInterceptors</code> field.</p>
     * @see java.util.List
     */
    private final List<RequestHandleInterceptor> handleInterceptors;

    /**
     * <code>RiceWebMvcAutoConfigure</code>
     * <p>Instantiates a new rice web mvc auto configure.</p>
     * @param loginProperties      {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @param userArgumentResolver {@link io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver} <p>The user argument resolver parameter is <code>DefaultUserInfoResolver</code> type.</p>
     * @param handleInterceptors   {@link java.util.List} <p>The handle interceptors parameter is <code>List</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver
     * @see java.util.List
     */
    public RiceWebMvcAutoConfigure(RiceLoginProperties loginProperties, DefaultUserInfoResolver userArgumentResolver, List<RequestHandleInterceptor> handleInterceptors) {
        this.loginProperties = loginProperties;
        this.userArgumentResolver = userArgumentResolver;
        this.handleInterceptors = handleInterceptors;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(this.userArgumentResolver);
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        List<String> skipUrls = this.loginProperties.getSkipUrls();
        if (GeneralUtils.isNotEmpty(this.handleInterceptors)) {
            for (RequestHandleInterceptor handleInterceptor : this.handleInterceptors) {
                InterceptorRegistration registration = registry.addInterceptor(handleInterceptor);
                if (GeneralUtils.isNotEmpty(skipUrls)) {
                    for (String url : skipUrls) {
                        registration.excludePathPatterns(url);
                    }
                }
            }
        }
    }
}
