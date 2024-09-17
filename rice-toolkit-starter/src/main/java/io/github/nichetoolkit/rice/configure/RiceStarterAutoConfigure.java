package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.resolver.DefaultMapArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.annotation.MapMethodProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * <code>RiceStarterAutoConfigure</code>
 * <p>The type rice starter auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
@ImportAutoConfiguration(value = {RiceInterceptorAutoConfigure.class})
public class RiceStarterAutoConfigure implements InitializingBean {
    private final RiceLoginProperties loginProperties;

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private final DefaultMapArgumentResolver mapArgumentResolver;

    /**
     * <code>RiceStarterAutoConfigure</code>
     * Instantiates a new rice starter auto configure.
     * @param loginProperties              {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>the login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @param requestMappingHandlerAdapter {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter} <p>the request mapping handler adapter parameter is <code>RequestMappingHandlerAdapter</code> type.</p>
     * @param mapArgumentResolver          {@link io.github.nichetoolkit.rice.resolver.DefaultMapArgumentResolver} <p>the map argument resolver parameter is <code>DefaultMapArgumentResolver</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
     * @see io.github.nichetoolkit.rice.resolver.DefaultMapArgumentResolver
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RiceStarterAutoConfigure(RiceLoginProperties loginProperties,
                                    RequestMappingHandlerAdapter requestMappingHandlerAdapter,
                                    DefaultMapArgumentResolver mapArgumentResolver) {

        this.loginProperties = loginProperties;
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
        this.mapArgumentResolver = mapArgumentResolver;
        log.debug("the auto configuration for [rice-starter] initiated");
    }

    @Override
    public void afterPropertiesSet() {
        if (loginProperties.getEnabled()) {
            resolveArgumentResolver();
        }
    }

    private void resolveArgumentResolver() {
        List<HandlerMethodArgumentResolver> customArgumentResolvers = new LinkedList<>();
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        if (GeneralUtils.isNotEmpty(argumentResolvers)) {
            for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
                if (argumentResolver instanceof MapMethodProcessor) {
                    customArgumentResolvers.add(mapArgumentResolver);
                }
                customArgumentResolvers.add(argumentResolver);
            }
            requestMappingHandlerAdapter.setArgumentResolvers(customArgumentResolvers);
        }
    }
}
