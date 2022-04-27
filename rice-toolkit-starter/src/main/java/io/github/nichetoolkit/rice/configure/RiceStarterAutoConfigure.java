package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.helper.LoginTokenHelper;
import io.github.nichetoolkit.rice.resolver.RiceMapArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.annotation.MapMethodProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>RiceSuperStarterAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
public class RiceStarterAutoConfigure implements InitializingBean {
    private final RiceLoginProperties loginProperties;

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private final RiceMapArgumentResolver mapArgumentResolver;

    @Autowired
    public RiceStarterAutoConfigure(RiceLoginProperties loginProperties,
                                  RequestMappingHandlerAdapter requestMappingHandlerAdapter,
                                  RiceMapArgumentResolver mapArgumentResolver
    ) {

        this.loginProperties = loginProperties;
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
        this.mapArgumentResolver = mapArgumentResolver;
        log.debug("================= login-auto-config initiated ！ ===================");
    }

    @Override
    public void afterPropertiesSet(){
        if (loginProperties.getEnabled()) {
            resolveArgumentResolver();
            LoginTokenHelper.init(loginProperties);
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
