package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.interceptor.RiceLoginRequestInterceptor;
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
public class RiceLoginAutoConfigure implements WebMvcConfigurer {

    private final RiceUserArgumentResolver userArgumentResolver;
    private final RiceLoginRequestInterceptor loginRequestInterceptor;
    private final RiceLoginProperties loginProperties;

    @Autowired
    public RiceLoginAutoConfigure(RiceUserArgumentResolver userArgumentResolver, RiceLoginRequestInterceptor loginRequestInterceptor, RiceLoginProperties loginProperties) {
        this.userArgumentResolver = userArgumentResolver;
        this.loginRequestInterceptor = loginRequestInterceptor;
        this.loginProperties = loginProperties;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(loginRequestInterceptor);
        List<String> skipUrls = loginProperties.getSkipUrls();
        if (GeneralUtils.isNotEmpty(skipUrls)) {
            for (String url : skipUrls) {
                registration.excludePathPatterns(url);
            }
        }
    }

}
