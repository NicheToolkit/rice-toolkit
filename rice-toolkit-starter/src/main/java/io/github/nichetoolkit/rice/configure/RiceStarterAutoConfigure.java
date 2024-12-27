package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestLogicMark;
import io.github.nichetoolkit.rice.defaults.DefaultAutoLogicMark;
import io.github.nichetoolkit.rice.resolver.RestIdResolver;
import io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver;
import io.github.nichetoolkit.rice.defaults.DefaultLongIdResolver;
import io.github.nichetoolkit.rice.defaults.DefaultStringIdResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.annotation.MapMethodProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * <code>RiceStarterAutoConfigure</code>
 * <p>The rice starter auto configure class.</p>
 * @see  org.springframework.beans.factory.InitializingBean
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.context.annotation.Configuration
 * @see  org.springframework.context.annotation.ComponentScan
 * @see  org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @author Cyan (snow22314@outlook.com)
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
     * @see  io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    private final RiceLoginProperties loginProperties;

    /**
     * <code>requestMappingHandlerAdapter</code>
     * {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter} <p>The <code>requestMappingHandlerAdapter</code> field.</p>
     * @see  org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
     */
    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    /**
     * <code>mapArgumentResolver</code>
     * {@link io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver} <p>The <code>mapArgumentResolver</code> field.</p>
     * @see  io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver
     */
    private final DefaultTokenContextResolver mapArgumentResolver;

    /**
     * <code>RiceStarterAutoConfigure</code>
     * <p>Instantiates a new rice starter auto configure.</p>
     * @param loginProperties {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @param requestMappingHandlerAdapter {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter} <p>The request mapping handler adapter parameter is <code>RequestMappingHandlerAdapter</code> type.</p>
     * @param mapArgumentResolver {@link io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver} <p>The map argument resolver parameter is <code>DefaultTokenContextResolver</code> type.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see  org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
     * @see  io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RiceStarterAutoConfigure(RiceLoginProperties loginProperties,
                                    RequestMappingHandlerAdapter requestMappingHandlerAdapter,
                                    DefaultTokenContextResolver mapArgumentResolver) {

        this.loginProperties = loginProperties;
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
        this.mapArgumentResolver = mapArgumentResolver;
        log.debug("The auto configuration for [rice-starter] initiated");
    }

    @Override
    public void afterPropertiesSet() {
        if (this.loginProperties.getEnabled()) {
            resolveArgumentResolver();
        }
    }

    /**
     * <code>resolveArgumentResolver</code>
     * <p>The resolve argument resolver method.</p>
     */
    private void resolveArgumentResolver() {
        List<HandlerMethodArgumentResolver> customArgumentResolvers = new LinkedList<>();
        List<HandlerMethodArgumentResolver> argumentResolvers = this.requestMappingHandlerAdapter.getArgumentResolvers();
        if (GeneralUtils.isNotEmpty(argumentResolvers)) {
            for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
                if (argumentResolver instanceof MapMethodProcessor) {
                    customArgumentResolvers.add(this.mapArgumentResolver);
                }
                customArgumentResolvers.add(argumentResolver);
            }
            this.requestMappingHandlerAdapter.setArgumentResolvers(customArgumentResolvers);
        }
    }

    /**
     * <code>defaultStringIdResolver</code>
     * <p>The default string id resolver method.</p>
     * @return  {@link io.github.nichetoolkit.rice.resolver.RestIdResolver} <p>The default string id resolver return object is <code>RestIdResolver</code> type.</p>
     * @see  io.github.nichetoolkit.rice.resolver.RestIdResolver
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(DefaultStringIdResolver.class)
    public RestIdResolver<String> defaultStringIdResolver() {
        return DefaultStringIdResolver.DEFAULT_RESOLVER;
    }

    /**
     * <code>defaultLongIdResolver</code>
     * <p>The default long id resolver method.</p>
     * @return  {@link io.github.nichetoolkit.rice.resolver.RestIdResolver} <p>The default long id resolver return object is <code>RestIdResolver</code> type.</p>
     * @see  io.github.nichetoolkit.rice.resolver.RestIdResolver
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(DefaultLongIdResolver.class)
    public RestIdResolver<Long> defaultLongIdResolver() {
        return DefaultLongIdResolver.DEFAULT_RESOLVER;
    }


    /**
     * <code>defaultAutoLogicMark</code>
     * <p>The default auto logic mark method.</p>
     * @param serviceProperties {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The service properties parameter is <code>RiceServiceProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceServiceProperties
     * @see  io.github.nichetoolkit.rice.RestLogicMark
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @return  {@link io.github.nichetoolkit.rice.RestLogicMark} <p>The default auto logic mark return object is <code>RestLogicMark</code> type.</p>
     */
    @Bean
    @ConditionalOnMissingBean(RestLogicMark.class)
    public RestLogicMark defaultAutoLogicMark(RiceServiceProperties serviceProperties) {
        return new DefaultAutoLogicMark(serviceProperties);
    }

}
