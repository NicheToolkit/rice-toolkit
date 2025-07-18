package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rice.RestLogicMark;
import io.github.nichetoolkit.rice.RestUserResolver;
import io.github.nichetoolkit.rice.defaults.DefaultAutoLogicMark;
import io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver;
import io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * <code>RiceServiceAutoConfigure</code>
 * <p>The rice service auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(RiceServiceProperties.class)
public class RiceServiceAutoConfigure {

    /**
     * <code>serviceProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The <code>serviceProperties</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceServiceProperties
     */
    private final RiceServiceProperties serviceProperties;

    /**
     * <code>RiceServiceAutoConfigure</code>
     * <p>Instantiates a new rice service auto configure.</p>
     * @param serviceProperties {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The service properties parameter is <code>RiceServiceProperties</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceServiceProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RiceServiceAutoConfigure(RiceServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
        log.debug("The auto configuration for [rice-service] initiated");
    }

    /**
     * <code>defaultAutoLogicMark</code>
     * <p>The default auto logic mark method.</p>
     * @return {@link io.github.nichetoolkit.rice.RestLogicMark} <p>The default auto logic mark return object is <code>RestLogicMark</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestLogicMark
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(RestLogicMark.class)
    public RestLogicMark defaultAutoLogicMark() {
        return new DefaultAutoLogicMark(this.serviceProperties);
    }

    @Bean
    @ConditionalOnMissingBean(DefaultTokenContextResolver.class)
    public DefaultTokenContextResolver tokenContextResolver() {
        return new DefaultTokenContextResolver();
    }

    @Bean
    @ConditionalOnBean(RestUserResolver.class)
    @ConditionalOnMissingBean(DefaultUserInfoResolver.class)
    public DefaultUserInfoResolver userInfoResolver(List<RestUserResolver> userResolvers) {
        return new DefaultUserInfoResolver(userResolvers);
    }

    @Bean
    @ConditionalOnMissingBean({DefaultUserInfoResolver.class,RestUserResolver.class})
    public DefaultUserInfoResolver userInfoResolver() {
        return new DefaultUserInfoResolver();
    }

}
