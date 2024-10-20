package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.configure.RestIdentityProperties;
import io.github.nichetoolkit.rest.identity.IdentityFactory;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestIdResolver;
import io.github.nichetoolkit.rice.defaults.DefaultMapArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@ImportAutoConfiguration(value = {RiceLoginAutoConfigure.class})
public class RiceStarterAutoConfigure implements InitializingBean {
    /**
     * <code>loginProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The <code>loginProperties</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    private final RiceLoginProperties loginProperties;

    /**
     * <code>requestMappingHandlerAdapter</code>
     * {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter} <p>The <code>requestMappingHandlerAdapter</code> field.</p>
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
     */
    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    /**
     * <code>mapArgumentResolver</code>
     * {@link io.github.nichetoolkit.rice.defaults.DefaultMapArgumentResolver} <p>The <code>mapArgumentResolver</code> field.</p>
     * @see io.github.nichetoolkit.rice.defaults.DefaultMapArgumentResolver
     */
    private final DefaultMapArgumentResolver mapArgumentResolver;

    /**
     * <code>RiceStarterAutoConfigure</code>
     * <p>Instantiates a new rice starter auto configure.</p>
     * @param loginProperties              {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @param requestMappingHandlerAdapter {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter} <p>The request mapping handler adapter parameter is <code>RequestMappingHandlerAdapter</code> type.</p>
     * @param mapArgumentResolver          {@link io.github.nichetoolkit.rice.defaults.DefaultMapArgumentResolver} <p>The map argument resolver parameter is <code>DefaultMapArgumentResolver</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
     * @see io.github.nichetoolkit.rice.defaults.DefaultMapArgumentResolver
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RiceStarterAutoConfigure(RiceLoginProperties loginProperties,
                                    RequestMappingHandlerAdapter requestMappingHandlerAdapter,
                                    DefaultMapArgumentResolver mapArgumentResolver) {

        this.loginProperties = loginProperties;
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
        this.mapArgumentResolver = mapArgumentResolver;
        log.debug("The auto configuration for [rice-starter] initiated");
    }

    @Override
    public void afterPropertiesSet() {
        if (loginProperties.getEnabled()) {
            resolveArgumentResolver();
        }
    }

    /**
     * <code>resolveArgumentResolver</code>
     * <p>The argument resolver method.</p>
     */
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

    @Bean
    @ConditionalOnMissingBean(RestIdResolver.class)
    public RestIdResolver<String> defaultStringIdResolver() {
        return new RestIdResolver<String>() {
            @Override
            public <M extends RestId<String>> String resolve(M model) throws RestException {
                return IdentityUtils.generateString();
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(RestIdResolver.class)
    public RestIdResolver<Long> defaultLongIdResolver() {
        return new RestIdResolver<Long>() {
            @Override
            public <M extends RestId<Long>> Long resolve(M model) throws RestException {
                return IdentityUtils.generateLong();
            }
        };
    }
}
