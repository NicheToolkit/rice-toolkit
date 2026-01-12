package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rice.RestLogicMark;
import io.github.nichetoolkit.rice.RestUserResolver;
import io.github.nichetoolkit.rice.defaults.DefaultAutoLogicMark;
import io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver;
import io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * <code>RiceServiceAutoConfigure</code>
 * <p>The rice service auto configure class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.boot.autoconfigure.AutoConfiguration
 * @see  org.springframework.boot.autoconfigure.AutoConfigureBefore
 * @see  org.springframework.boot.context.properties.EnableConfigurationProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@AutoConfigureBefore(RiceLoginAutoConfigure.class)
@EnableConfigurationProperties(RiceServiceProperties.class)
public class RiceServiceAutoConfigure {

    /**
     * <code>serviceProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The <code>serviceProperties</code> field.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceServiceProperties
     */
    private final RiceServiceProperties serviceProperties;

    /**
     * <code>RiceServiceAutoConfigure</code>
     * <p>Instantiates a new rice service auto configure.</p>
     * @param serviceProperties {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The service properties parameter is <code>RiceServiceProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceServiceProperties
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RiceServiceAutoConfigure(RiceServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
        log.debug("The auto configuration for [rice-service] initiated");
    }

    /**
     * <code>defaultAutoLogicMark</code>
     * <p>The default auto logic mark method.</p>
     * @return  {@link io.github.nichetoolkit.rice.RestLogicMark} <p>The default auto logic mark return object is <code>RestLogicMark</code> type.</p>
     * @see  io.github.nichetoolkit.rice.RestLogicMark
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(RestLogicMark.class)
    public RestLogicMark defaultAutoLogicMark() {
        return new DefaultAutoLogicMark(this.serviceProperties);
    }

    /**
     * <code>tokenContextResolver</code>
     * <p>The token context resolver method.</p>
     * @return  {@link io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver} <p>The token context resolver return object is <code>DefaultTokenContextResolver</code> type.</p>
     * @see  io.github.nichetoolkit.rice.defaults.DefaultTokenContextResolver
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(DefaultTokenContextResolver.class)
    public DefaultTokenContextResolver tokenContextResolver() {
        return new DefaultTokenContextResolver();
    }

    /**
     * <code>userInfoResolver</code>
     * <p>The user info resolver method.</p>
     * @param userResolvers {@link java.util.List} <p>The user resolvers parameter is <code>List</code> type.</p>
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnBean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @return  {@link io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver} <p>The user info resolver return object is <code>DefaultUserInfoResolver</code> type.</p>
     */
    @Bean
    @ConditionalOnBean(RestUserResolver.class)
    @ConditionalOnMissingBean(DefaultUserInfoResolver.class)
    public DefaultUserInfoResolver userInfoResolver(List<RestUserResolver> userResolvers) {
        return new DefaultUserInfoResolver(userResolvers);
    }

    /**
     * <code>userInfoResolver</code>
     * <p>The user info resolver method.</p>
     * @return  {@link io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver} <p>The user info resolver return object is <code>DefaultUserInfoResolver</code> type.</p>
     * @see  io.github.nichetoolkit.rice.defaults.DefaultUserInfoResolver
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean({DefaultUserInfoResolver.class,RestUserResolver.class})
    public DefaultUserInfoResolver userInfoResolver() {
        return new DefaultUserInfoResolver();
    }

}
