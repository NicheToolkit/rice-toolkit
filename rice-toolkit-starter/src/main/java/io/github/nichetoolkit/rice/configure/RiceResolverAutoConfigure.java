package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.method.annotation.MapMethodProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * <code>RiceResolverAutoConfigure</code>
 * <p>The rice resolver auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@AutoConfigureAfter(RiceServiceAutoConfigure.class)
@ConditionalOnProperty(value = "nichetoolkit.rice.login.enabled", havingValue = "true")
public class RiceResolverAutoConfigure {

    /**
     * <code>mappingHandlerAdapter</code>
     * {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter} <p>The <code>mappingHandlerAdapter</code> field.</p>
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
     */
    private final RequestMappingHandlerAdapter mappingHandlerAdapter;
    /**
     * <code>mapArgumentResolver</code>
     * {@link io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver} <p>The <code>mapArgumentResolver</code> field.</p>
     * @see io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver
     */
    private final DefaultTokenContextResolver mapArgumentResolver;

    /**
     * <code>RiceResolverAutoConfigure</code>
     * <p>Instantiates a new rice resolver auto configure.</p>
     * @param mappingHandlerAdapter {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter} <p>The mapping handler adapter parameter is <code>RequestMappingHandlerAdapter</code> type.</p>
     * @param mapArgumentResolver   {@link io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver} <p>The map argument resolver parameter is <code>DefaultTokenContextResolver</code> type.</p>
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
     * @see io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver
     */
    public RiceResolverAutoConfigure(RequestMappingHandlerAdapter mappingHandlerAdapter, DefaultTokenContextResolver mapArgumentResolver) {
        this.mappingHandlerAdapter = mappingHandlerAdapter;
        this.mapArgumentResolver = mapArgumentResolver;
        resolveArgumentResolver();
        log.debug("The auto configuration for [rice-resolver] initiated");
    }

    /**
     * <code>resolveArgumentResolver</code>
     * <p>The resolve argument resolver method.</p>
     */
    private void resolveArgumentResolver() {
        List<HandlerMethodArgumentResolver> customArgumentResolvers = new LinkedList<>();
        List<HandlerMethodArgumentResolver> argumentResolvers = this.mappingHandlerAdapter.getArgumentResolvers();
        if (GeneralUtils.isNotEmpty(argumentResolvers)) {
            for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
                if (argumentResolver instanceof MapMethodProcessor) {
                    customArgumentResolvers.add(this.mapArgumentResolver);
                }
                customArgumentResolvers.add(argumentResolver);
            }
            this.mappingHandlerAdapter.setArgumentResolvers(customArgumentResolvers);
        }
    }

}
