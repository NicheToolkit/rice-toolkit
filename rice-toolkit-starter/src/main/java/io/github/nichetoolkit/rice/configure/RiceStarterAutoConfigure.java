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

@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
@ImportAutoConfiguration(value = {RiceInterceptorAutoConfigure.class})
public class RiceStarterAutoConfigure implements InitializingBean {
    private final RiceLoginProperties loginProperties;

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private final DefaultMapArgumentResolver mapArgumentResolver;

    @Autowired
    public RiceStarterAutoConfigure(RiceLoginProperties loginProperties,
                                    RequestMappingHandlerAdapter requestMappingHandlerAdapter,
                                    DefaultMapArgumentResolver mapArgumentResolver) {

        this.loginProperties = loginProperties;
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
        this.mapArgumentResolver = mapArgumentResolver;
        log.debug("================= login-auto-config initiated ！ ===================");
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
