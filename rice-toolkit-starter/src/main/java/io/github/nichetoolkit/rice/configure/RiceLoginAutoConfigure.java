package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rest.RestAccessValue;
import io.github.nichetoolkit.rest.RestLoggingKey;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rice.RestAfterLoginAdvice;
import io.github.nichetoolkit.rice.RestBeforeLoginAdvice;
import io.github.nichetoolkit.rice.RestBeforeLogoutAdvice;
import io.github.nichetoolkit.rice.advice.LoginAdvice;
import io.github.nichetoolkit.rice.constant.AdviceConstants;
import io.github.nichetoolkit.rice.defaults.DefaultAccessTokenFilter;
import io.github.nichetoolkit.rice.interceptor.DefaultAnnotationInterceptor;
import io.github.nichetoolkit.rice.interceptor.DefaultLoginInterceptor;
import io.github.nichetoolkit.rice.defaults.DefaultLoggingKeyResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <code>RiceLoginAutoConfigure</code>
 * <p>The rice login auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @since Jdk17
 */
@Slf4j
@AutoConfiguration
@AutoConfigureAfter(RiceServiceAutoConfigure.class)
@EnableConfigurationProperties({RestLogbackProperties.class, RiceLoginProperties.class})
public class RiceLoginAutoConfigure {
    /**
     * <code>logbackProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The <code>logbackProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    private final RestLogbackProperties logbackProperties;
    /**
     * <code>loginProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The <code>loginProperties</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    private final RiceLoginProperties loginProperties;

    /**
     * <code>RiceLoginAutoConfigure</code>
     * <p>Instantiates a new rice login auto configure.</p>
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @param loginProperties   {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    public RiceLoginAutoConfigure(RestLogbackProperties logbackProperties, RiceLoginProperties loginProperties) {
        this.logbackProperties = logbackProperties;
        this.loginProperties = loginProperties;
        log.debug("The auto configuration for [rice-login] initiated");
    }

    /**
     * <code>loggingKey</code>
     * <p>The logging key method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestLoggingKey} <p>The logging key return object is <code>RestLoggingKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestLoggingKey
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     */
    @Bean
    @Primary
    public RestLoggingKey loggingKey() {
        return new DefaultLoggingKeyResolver(this.logbackProperties, this.loginProperties);
    }

    /**
     * <code>accessTokenFilter</code>
     * <p>The access token filter method.</p>
     * @param accessValue {@link io.github.nichetoolkit.rest.RestAccessValue} <p>The access value parameter is <code>RestAccessValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.defaults.DefaultAccessTokenFilter} <p>The access token filter return object is <code>DefaultAccessTokenFilter</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestAccessValue
     * @see io.github.nichetoolkit.rice.defaults.DefaultAccessTokenFilter
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.core.annotation.Order
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Order(value = Ordered.HIGHEST_PRECEDENCE + 101)
    @ConditionalOnBean(RestAccessValue.class)
    @ConditionalOnMissingBean(DefaultAccessTokenFilter.class)
    public DefaultAccessTokenFilter accessTokenFilter(RestAccessValue accessValue) {
        return new DefaultAccessTokenFilter(this.loginProperties, accessValue);
    }

    /**
     * <code>accessTokenFilter</code>
     * <p>The access token filter method.</p>
     * @return {@link io.github.nichetoolkit.rice.defaults.DefaultAccessTokenFilter} <p>The access token filter return object is <code>DefaultAccessTokenFilter</code> type.</p>
     * @see io.github.nichetoolkit.rice.defaults.DefaultAccessTokenFilter
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.core.annotation.Order
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Order(value = Ordered.HIGHEST_PRECEDENCE + 101)
    @ConditionalOnMissingBean({DefaultAccessTokenFilter.class, RestAccessValue.class})
    public DefaultAccessTokenFilter accessTokenFilter() {
        return new DefaultAccessTokenFilter(this.loginProperties);
    }

    /**
     * <code>loginInterceptor</code>
     * <p>The login interceptor method.</p>
     * @param loginAdvices {@link java.util.List} <p>The login advices parameter is <code>List</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.interceptor.DefaultLoginInterceptor} <p>The login interceptor return object is <code>DefaultLoginInterceptor</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rice.interceptor.DefaultLoginInterceptor
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.core.annotation.Order
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Order(AdviceConstants.LOGIN_ORDER)
    @ConditionalOnBean(LoginAdvice.class)
    @ConditionalOnMissingBean(DefaultLoginInterceptor.class)
    public DefaultLoginInterceptor loginInterceptor(List<LoginAdvice> loginAdvices) {
        return new DefaultLoginInterceptor(this.loginProperties, loginAdvices);
    }

    /**
     * <code>loginInterceptor</code>
     * <p>The login interceptor method.</p>
     * @return {@link io.github.nichetoolkit.rice.interceptor.DefaultLoginInterceptor} <p>The login interceptor return object is <code>DefaultLoginInterceptor</code> type.</p>
     * @see io.github.nichetoolkit.rice.interceptor.DefaultLoginInterceptor
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.core.annotation.Order
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Order(AdviceConstants.LOGIN_ORDER)
    @ConditionalOnMissingBean({DefaultLoginInterceptor.class, LoginAdvice.class})
    public DefaultLoginInterceptor loginInterceptor() {
        return new DefaultLoginInterceptor(this.loginProperties);
    }

    /**
     * <code>annotationInterceptor</code>
     * <p>The annotation interceptor method.</p>
     * @param logoutAdvices {@link java.util.Optional} <p>The logout advices parameter is <code>Optional</code> type.</p>
     * @param beforeAdvice  {@link java.util.Optional} <p>The before advice parameter is <code>Optional</code> type.</p>
     * @param afterAdvice   {@link java.util.Optional} <p>The after advice parameter is <code>Optional</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.interceptor.DefaultAnnotationInterceptor} <p>The annotation interceptor return object is <code>DefaultAnnotationInterceptor</code> type.</p>
     * @see java.util.Optional
     * @see io.github.nichetoolkit.rice.interceptor.DefaultAnnotationInterceptor
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.core.annotation.Order
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Order(AdviceConstants.ANNOTATION_ORDER)
    @ConditionalOnMissingBean(DefaultAnnotationInterceptor.class)
    public DefaultAnnotationInterceptor annotationInterceptor(Optional<List<RestBeforeLogoutAdvice<?>>> logoutAdvices, Optional<List<RestBeforeLoginAdvice<?>>> beforeAdvice, Optional<List<RestAfterLoginAdvice<?>>> afterAdvice) {
        return new DefaultAnnotationInterceptor(logoutAdvices.orElse(new ArrayList<>()),beforeAdvice.orElse(new ArrayList<>()), afterAdvice.orElse(new ArrayList<>()));
    }


}
